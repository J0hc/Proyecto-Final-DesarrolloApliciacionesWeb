/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.domain.Mesa;
import com.Tienda.domain.Reserva;
import com.Tienda.service.MesaService;
import com.Tienda.service.ReservaService;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private MesaService mesaService;

    @GetMapping("/listado")
    public String listadoReserva(Model model) {
        var reservas = reservaService.getReservas(false);
        model.addAttribute("reservas", reservas);
        model.addAttribute("totalReservas", reservas.size());

        return "/reserva/listado";
    }

    @GetMapping("/mesas-disponibles")
    @ResponseBody
    public List<Mesa> getMesasDisponibles(@RequestParam int personas, @RequestParam String fecha) {
        LocalDateTime fechaReserva = LocalDateTime.parse(fecha);
        return mesaService.getMesasFiltro(personas, fechaReserva);
    }

    @PostMapping("/guardar")
    public String reservaGuardar(@ModelAttribute Reserva reserva, Model model, HttpSession session) {
        // Buscar la mesa seleccionada por ID
        reserva.setEstado(Boolean.TRUE);

        session.setAttribute("reserva", reserva);
        // Obtienes las mesas disponibles para la siguiente parte
        List<Mesa> mesasDisponibles = mesaService.getMesasFiltro(
                reserva.getCantidadPersonas(), reserva.getFechaReserva());
        model.addAttribute("mesasDisponibles", mesasDisponibles);

        return "redirect:/reserva/seleccionMesa";

    }

    @PostMapping("/guardarPaso2")
    public String guardarPaso2(HttpSession session, @RequestParam Long mesaId) {

        Reserva reserva = (Reserva) session.getAttribute("reserva");

        // Obtener la mesa seleccionada por ID
        Mesa mesa = mesaService.getMesa(mesaId);

        reservaService.save(reserva);

        // Asignar la reserva a la mesa
        mesa.setReserva(reserva);

        // Asegurarnos de que la reserva tiene la lista de mesas correcta
        if (reserva.getMesas() == null) {
            reserva.setMesas(new ArrayList<Mesa>());  // Si no tiene mesas, inicializamos la lista
        }

        // Añadir la mesa seleccionada a la lista de mesas de la reserva
        reserva.getMesas().add(mesa);  // Agregamos la mesa seleccionada a la reserva

        // Guardar la mesa y la reserva nuevamente
        mesaService.save(mesa);  // Guardamos la mesa con la referencia de la reserva
        reservaService.save(reserva);  // Guardamos la reserva con la lista de mesas actualizada

        return "redirect:/reserva/listado";  // Redirigir a la lista de reservas
    }

    @GetMapping("/seleccionMesa")
    public String mostrarSeleccionMesa(HttpSession session, Model model) {
        Reserva reserva = (Reserva) session.getAttribute("reserva");

        if (reserva == null) {
            return "redirect:/reserva/error";
        }

        List<Mesa> mesasDisponibles = mesaService.getMesasFiltro(
                reserva.getCantidadPersonas(), reserva.getFechaReserva()); // O la lógica para obtener las mesas
        model.addAttribute("mesasDisponibles", mesasDisponibles); // Agregar las mesas al modelo

        return "reserva/seleccionMesa"; // El nombre de la plantilla en la carpeta templates/reserva
    }
    
    @GetMapping("/eliminar/{idReserva}")
    public String eliminar(@PathVariable Long idReserva){
        
        Mesa mesa = mesaService.obtenerMesaPorReservaId(idReserva);
        
        if( mesa != null){
            mesa.setReserva(null);
            mesaService.save(mesa);
            
        }
        
        reservaService.delete(idReserva);
        
        return "redirect:/reserva/listado";
        
        
        
        
    }
    
    

}
