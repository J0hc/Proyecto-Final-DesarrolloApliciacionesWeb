package com.CoffeLife.dao;

import com.CoffeLife.domain.Descuento;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DescuentoDao extends JpaRepository<Descuento, Long> {
    List<Descuento> findByFechaFinAfter(LocalDateTime ahora);
}
