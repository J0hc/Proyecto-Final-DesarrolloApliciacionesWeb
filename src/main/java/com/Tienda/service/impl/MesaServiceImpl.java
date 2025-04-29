/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.MesaDao;
import com.Tienda.domain.Mesa;
import com.Tienda.service.MesaService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MesaServiceImpl implements MesaService {
    
    @Autowired 
    MesaDao mesaDao;
    
    @Override
    @Transactional(readOnly = true) 
    public List<Mesa> getMesas() {
        return mesaDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true) 
    public List<Mesa> getMesasFiltro(int personas, LocalDateTime fecha) {
        
        List<Mesa> mesas = mesaDao.findAll();
        
        // Filtrar mesas que no tengan reserva asignada (por ejemplo, si la mesa no tiene reserva o está disponible)
        return mesas.stream()
                .filter(mesa -> mesa.getReserva() == null || mesa.getReserva().getIdReserva() == null)
                .filter(mesa -> mesa.getCapacidad() >= personas )
                .collect(Collectors.toList());
    }
    
    @Override
    public Mesa getMesa(Long mesaId){
        return mesaDao.findById(mesaId).orElse(null);
    }
    
     @Override
    public void save(Mesa mesa) {
        mesaDao.save(mesa);
    }

    @Override
    public void delete(Mesa mesa) {
        mesaDao.delete(mesa);
    }
    
    @Override
    public void liberarMesa(Mesa mesa) {
        // Liberamos la mesa (ponemos el campo reserva_id en null)
        mesa.setReserva(null);
        mesaDao.save(mesa);
    }
    
    @Override
    public Mesa obtenerMesaPorReservaId(Long idReserva){
        return mesaDao.findByReservaIdReserva(idReserva);
        
    }
    
    
    
    

    
    
}
