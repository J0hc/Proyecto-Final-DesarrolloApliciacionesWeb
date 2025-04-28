package com.CoffeLife.service.impl;

import com.CoffeLife.dao.UsuarioDao;
import com.CoffeLife.domain.Rol;
import com.CoffeLife.domain.Usuario;
import com.CoffeLife.service.CorreoServices;
import com.CoffeLife.service.UsuarioDetailsService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    CorreoServices correoService;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        
    session.removeAttribute("UsuarioImagen");
        session.setAttribute("UsuarioImagen", usuario.getRutaImagen());

       var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(/*"ROLE_"+*/rol.getNombre()));
        }
        
        System.out.println("Roles del usuario: ");
        for (Rol rol : usuario.getRoles()) {
            System.out.println(rol.getNombre());
        }
  
        System.out.println("Autoridades asignadas:");
        roles.forEach(r -> System.out.println(r.getAuthority()));

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
