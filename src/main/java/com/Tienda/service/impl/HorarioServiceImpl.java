/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.HorarioDao;
import com.Tienda.domain.Horario;
import com.Tienda.service.HorarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioDao horarioDao;
    
    @Override
    @Transactional(readOnly = true) 
    public List<Horario> getHorarios() {
        return horarioDao.findAll();
    }
    
   @Override
   @Transactional(readOnly = true)
    public Horario getHorario(Horario horario){
       
        Optional<Horario> horarioOpt = horarioDao.findById(horario.getIdHorario());

        return horarioOpt.orElse(null);
        
    }
    
   @Override
    public void save(Horario horario){
        horarioDao.save(horario);
    }
    
    @Override
    public void delete(Horario horario){
        horarioDao.delete(horario);
    }
    
    @Override
    @Transactional(readOnly = true) 
    public Horario getHorarioById(Long idHorario){
        Horario horario = horarioDao.findByIdHorario(idHorario);
        
        return horario;
    }
}
