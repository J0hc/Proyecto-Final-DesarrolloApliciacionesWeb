/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.domain.Horario;
import com.Tienda.service.HorarioService;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/horario")
public class HorarioController {
    
    @Autowired
    private HorarioService horarioService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var horarios = horarioService.getHorarios();
        model.addAttribute("horarios", horarios);
        model.addAttribute("totalHorarios", horarios.size());
        return "/horario/listado";
    }
    
    @GetMapping("/nuevo")
    public String horarioNuevo(Horario horario) {
        return "/horario/modifica";
    }
    
    @PostMapping("/guardar")
    public String horarioGuardar(Horario horario) {        
        horarioService.save(horario);
        return "redirect:/horario/listado";
    }
    
    @GetMapping("/eliminar/{idHorario}")
    public String horarioEliminar(Horario horario) {
        horarioService.delete(horario);
        return "redirect:/horario/listado";
    }
    
    @GetMapping("/modificar/{idHorario}")
    public String horarioModificar(Horario horario, Model model) {
        horario = horarioService.getHorario(horario);
        model.addAttribute("horario", horario);
        return "/horario/modifica";
    }
    
}
