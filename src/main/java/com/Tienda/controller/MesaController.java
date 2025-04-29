/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.domain.Mesa;
import com.Tienda.service.MesaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mesa")
public class MesaController {
    
    @Autowired
    private MesaService mesaService;
    
    @GetMapping("/listado")
    public String listadoMesa(Model model) {
        var mesas = mesaService.getMesas();
        model.addAttribute("mesas", mesas);
        model.addAttribute("totalMesas", mesas.size());

        return "/mesa/listado";
    }
    
    @PostMapping("/guardar")
    public String mesaGuardar(Mesa mesa){
        mesaService.save(mesa);
        return "redirect:/mesa/listado";
    }
    
    @GetMapping("/eliminar/{idMesa}")
    public String eliminar(Mesa mesa){
        mesaService.delete(mesa);
        return "redirect:/mesa/listado";
    }
    
    
}
