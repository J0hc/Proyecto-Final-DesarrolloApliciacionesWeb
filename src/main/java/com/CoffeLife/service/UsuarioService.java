package com.CoffeLife.service;

import com.CoffeLife.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    
    public List<Usuario> getUsuarios();
    
    public Usuario getUsuario(Usuario usuario);
    
    public Usuario findByUsername(String username);
    
    public Usuario findByUsernameAndPassword(String username, String password);
    
    public Usuario findByUsernameOrCorreo(String username, String correo);
    
    public boolean existsByUsernameOrCorreo(String username, String correo);
 
    public void save(Usuario usuario, boolean creaRol);
    
    public void delete(Usuario usuario);
    
}