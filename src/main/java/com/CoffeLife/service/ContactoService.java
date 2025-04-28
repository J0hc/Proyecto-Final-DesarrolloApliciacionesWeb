package com.CoffeLife.service;

import com.CoffeLife.domain.Contacto;
import java.util.List;

public interface ContactoService {

    public List<Contacto> getContacto(boolean activos);
    
    public Contacto getContacto(Contacto contacto);
    
    public void save(Contacto contacto);
    
    public void delete(Contacto contacto);
}
