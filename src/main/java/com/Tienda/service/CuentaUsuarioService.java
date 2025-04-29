/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.Tienda.service;

import com.Tienda.domain.CuentaUsuario;
import java.util.List;

public interface CuentaUsuarioService {
    
    // Se obtiene un listado de cuentaUsuarios en un List
    public List<CuentaUsuario> getCuentaUsuarios(boolean activos);
    
   // Se obtiene un CuentaUsuario, a partir del id de un cuentaUsuario
    public CuentaUsuario getCuentaUsuario(CuentaUsuario cuentaUsuario);
    
    // Se inserta un nuevo cuentaUsuario si el id del cuentaUsuario esta vacío
    // Se actualiza un cuentaUsuario si el id del cuentaUsuario NO esta vacío
    public void save(CuentaUsuario cuentaUsuario);
    
    // Se elimina el cuentaUsuario que tiene el id pasado por parámetro
    public void delete(CuentaUsuario cuentaUsuario);
     
}