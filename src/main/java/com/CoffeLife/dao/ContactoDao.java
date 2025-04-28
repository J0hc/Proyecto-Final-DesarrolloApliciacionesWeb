package com.CoffeLife.dao;

import com.CoffeLife.domain.Contacto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactoDao extends JpaRepository<Contacto, Long> {
   
}