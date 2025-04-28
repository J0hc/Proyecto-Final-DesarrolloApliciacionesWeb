package com.CoffeLife.dao;


import com.CoffeLife.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao extends JpaRepository <Categoria,Long> {
    
}