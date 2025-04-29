/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.service;

import com.Tienda.domain.Mesa;
import java.time.LocalDateTime;
import java.util.List;




public interface MesaService {
    
    public List<Mesa> getMesas();
    
    public List<Mesa> getMesasFiltro(int personas, LocalDateTime fecha);

    public Mesa getMesa(Long mesaId);

    public void save(Mesa mesa);
    
    public void delete(Mesa mesa);
    
    public void liberarMesa(Mesa mesa);
    
    public Mesa obtenerMesaPorReservaId(Long idReserva);
    
}
