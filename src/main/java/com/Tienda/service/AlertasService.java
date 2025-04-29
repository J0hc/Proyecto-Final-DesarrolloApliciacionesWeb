package com.Tienda.service;

import com.Tienda.domain.Alertas;
import java.util.List;

public interface AlertasService {
    
    // Se obtiene un listado de alertass en un List
    public List<Alertas> getAlertass(boolean activos);
    
   // Se obtiene un Alertas, a partir del id de un alertas
    public Alertas getAlertas(Alertas alertas);
    
    // Se inserta un nuevo alertas si el id del alertas esta vacío
    // Se actualiza un alertas si el id del alertas NO esta vacío
    public void save(Alertas alertas);
    
    // Se elimina el alertas que tiene el id pasado por parámetro
    public void delete(Alertas alertas);
     
}