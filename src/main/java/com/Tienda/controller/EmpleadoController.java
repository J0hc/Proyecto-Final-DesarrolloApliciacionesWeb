package com.Tienda.controller;

import com.Tienda.dao.EmpleadoDao;
import com.Tienda.domain.Empleado;
import com.Tienda.domain.Horario;
import com.Tienda.service.EmpleadoService;
import com.Tienda.service.HorarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private HorarioService horarioService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var empleados = empleadoService.getEmpleados(true);
        model.addAttribute("empleados", empleados);
        model.addAttribute("totalEmpleados", empleados.size());
        return "/empleado/listado";
    }

    @GetMapping("/nuevo")
    public String empleadoNuevo(Empleado empleado) {
        return "/empleado/modifica";
    }

    @PostMapping("/guardar")
    public String empleadoGuardar(Empleado empleado, @RequestParam(name = "idHorario", required = false) Long idHorario) {

        //Si el empleado no existe
        if (empleado.getIdEmpleado() == null) {
            empleado.setEstado(true);
            Horario horario = horarioService.getHorarioById(idHorario);

            if (horario == null) {
                throw new IllegalArgumentException("No se encontró un horario con el ID: " + idHorario);
            }
            empleado.setHorario(horario);
        }

        empleadoService.save(empleado);
        return "redirect:/empleado/listado";
    }

    @GetMapping("/eliminar/{idEmpleado}")
    public String empleadoEliminar(Empleado empleado) {
        empleadoService.delete(empleado);
        return "redirect:/empleado/listado";
    }

    @GetMapping("/modificar/{idEmpleado}")
    public String empleadoModificar(Empleado empleado, Model model) {
        empleado = empleadoService.getEmpleado(empleado);
        model.addAttribute("empleado", empleado);
        return "/empleado/modifica";
    }

}
