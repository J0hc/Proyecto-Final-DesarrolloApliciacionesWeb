package com.CoffeLife.dao;

import com.CoffeLife.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository <Factura,Long> {
     
}
