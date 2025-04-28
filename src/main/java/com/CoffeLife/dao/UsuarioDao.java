package com.CoffeLife.dao;

import com.CoffeLife.domain.Usuario;
import com.CoffeLife.domain.producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDao extends JpaRepository <Usuario,Long> {
    
    public Usuario findByUsername(String username);
    
    public Usuario findByUsernameAndPassword(String username, String password);
    
    public Usuario findByUsernameOrCorreo(String username, String correo);
    
    public boolean existsByUsernameOrCorreo(String username, String correo);
}