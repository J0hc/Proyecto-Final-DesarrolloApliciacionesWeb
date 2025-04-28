package com.CoffeLife.service;

import com.CoffeLife.domain.Categoria;
import com.CoffeLife.domain.Galeria;
import java.util.List;

public interface GaleriaService {
    
    public List<Galeria> getGaleria(boolean activos);
    
    public Galeria getGaleria(Galeria galeria);
    
    public void save(Galeria galeria);
    
    public void delete(Galeria galeria);
}