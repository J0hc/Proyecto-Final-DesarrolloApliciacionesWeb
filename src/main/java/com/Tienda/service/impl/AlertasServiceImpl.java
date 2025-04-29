/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.domain.Alertas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Tienda.dao.AlertasDao;
import com.Tienda.service.AlertasService;

@Service
public class AlertasServiceImpl implements AlertasService {

    @Autowired
    private AlertasDao alertasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Alertas> getAlertass(boolean activos) {
        var lista = alertasDao.findAll();
        if (activos) {
            //lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Alertas getAlertas(Alertas alertas) {
        return alertasDao.findById(alertas.getIdAlertas()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Alertas alertas) {
        alertasDao.save(alertas);
    }

    @Override
    @Transactional
    public void delete(Alertas alertas) {
        alertasDao.delete(alertas);
    }
}
