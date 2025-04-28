package com.CoffeLife.service;

import com.CoffeLife.domain.Categoria;
import com.CoffeLife.domain.Promocion;
import java.util.List;

public interface PromocionService {
    
    public List<Promocion> listarPromociones();
    public void guardarPromocion(Promocion promocion);
    public Promocion guardar(Promocion promocion);
    public void eliminarPromocion(Long id);
    public Promocion obtenerPromocionPorId(Long id);
}


