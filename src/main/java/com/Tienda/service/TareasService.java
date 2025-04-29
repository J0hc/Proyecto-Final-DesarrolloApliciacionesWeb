package com.Tienda.service;

import com.Tienda.domain.Tareas;
import java.util.List;

public interface TareasService {
    
    // Se obtiene un listado de tareas en un List
    public List<Tareas> getTareas(boolean activos);
    
   // Se obtiene un Tareas, a partir del id de un tareas
    public Tareas getTareas(Tareas tareas);
    
    // Se inserta un nuevo tareas si el id del tareas esta vacío
    // Se actualiza un tareas si el id del tareas NO esta vacío
    public void save(Tareas tareas);
    
    // Se elimina el tareas que tiene el id pasado por parámetro
    public void delete(Tareas tareas);
     
}