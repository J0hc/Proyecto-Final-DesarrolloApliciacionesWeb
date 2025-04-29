/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.dao;

import com.Tienda.domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HorarioDao extends JpaRepository<Horario, Long> {
    Horario findByIdHorario(Long idHorario);

    
}
