package com.CoffeLife.dao;

import com.CoffeLife.domain.Rol;
import com.CoffeLife.domain.producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolDao extends JpaRepository <Rol,Long> {
    
}