package com.CoffeLife.service;

import com.CoffeLife.domain.Descuento;
import java.util.List;

public interface DescuentoService {
    List<Descuento> obtenerDescuentosActivos();
    void guardarDescuento(Descuento descuento);
    void eliminarDescuento(Long id);
    Descuento buscarPorId(Long id);
    
}

