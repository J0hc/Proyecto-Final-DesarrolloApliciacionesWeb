/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.ReservaDao;
import com.Tienda.domain.Reserva;
import com.Tienda.domain.producto;
import com.Tienda.service.ReservaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReservaServiceImpl implements ReservaService {
    
    @Autowired
    private ReservaDao reservaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Reserva> getReservas(boolean activos) {
        var lista = reservaDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Reserva getReserva(Reserva reserva) {
        return reservaDao.findById(reserva.getIdReserva()).orElse(null);
    }

    @Override
     @Transactional
    public void save(Reserva reserva) {
        reservaDao.save(reserva);
    }

    @Override
    @Transactional
    public void delete(Long idReserva) {
        
        Optional<Reserva> reserva = reservaDao.findById(idReserva);
        
        if (reserva.isPresent()) {
            reservaDao.delete(reserva.get());
        }
    }
    
    
}
