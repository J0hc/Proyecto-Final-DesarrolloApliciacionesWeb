/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.domain.Tareas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Tienda.dao.TareasDao;
import com.Tienda.service.TareasService;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasDao tareasDao;

    /*@Override
    @Transactional(readOnly = true)
    public List<Tareas> getTareass(boolean activos) {
        var lista = tareasDao.findAll();
        if (activos) {
            //lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
*/
    @Override
    @Transactional(readOnly = true)
    public Tareas getTareas(Tareas tareas) {
        return tareasDao.findById(tareas.getIdTareas()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Tareas tareas) {
        tareasDao.save(tareas);
    }

    @Override
    @Transactional
    public void delete(Tareas tareas) {
        tareasDao.delete(tareas);
    }

    @Override
    public List<Tareas> getTareas(boolean activos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
