package com.CoffeLife.dao;

import com.CoffeLife.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository <Venta,Long> {
     
}
