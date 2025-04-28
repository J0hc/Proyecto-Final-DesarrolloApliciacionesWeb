package com.CoffeLife.service.impl;
import com.CoffeLife.dao.RolDao;
import com.CoffeLife.dao.UsuarioDao;
import com.CoffeLife.domain.Rol;
import com.CoffeLife.domain.Usuario;
import com.CoffeLife.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private RolDao rolDao;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        var lista = usuarioDao.findAll();
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }  
    
    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsernameAndPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }  
    
    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsernameOrCorreo(String username, String correo) {
        return usuarioDao.findByUsernameAndPassword(username, correo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsernameOrCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    } 

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean creaRol) {
        usuario = usuarioDao.save(usuario);
        
        if(creaRol){
            Rol rol = new Rol();
            rol.setIdUsuario(usuario.getIdUsuario());
            rol.setNombre("ROL_USER");
            rolDao.save(rol);
            
        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
