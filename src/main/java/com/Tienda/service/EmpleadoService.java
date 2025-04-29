package com.Tienda.service;

import com.Tienda.domain.Empleado;
import java.util.List;

public interface EmpleadoService {
    
    // Se obtiene un listado de empleados en un List
    public List<Empleado> getEmpleados(boolean activos);
    
   // Se obtiene un Empleado, a partir del id de un empleado
    public Empleado getEmpleado(Empleado empleado);
    
    // Se inserta un nuevo empleado si el id del empleado esta vacío
    // Se actualiza un empleado si el id del empleado NO esta vacío
    public void save(Empleado empleado);
    
    // Se elimina el empleado que tiene el id pasado por parámetro
    public void delete(Empleado empleado);
     
}