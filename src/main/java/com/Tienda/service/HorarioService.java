/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.service;

import com.Tienda.domain.Horario;
import java.util.List;
import java.util.Optional;




public interface HorarioService  {
    
    
    public List<Horario> getHorarios();

    public Horario getHorario(Horario horario);
    
    public Horario getHorarioById(Long idHorario);
    
    public void save(Horario horario);
    
    public void delete(Horario horario);
       
}
