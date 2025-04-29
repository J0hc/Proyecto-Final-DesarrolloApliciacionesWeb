package com.Tienda.controller;

import com.Tienda.dao.TareasDao;
import com.Tienda.domain.Tareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TareasController {

    @Autowired
    private TareasDao TareasDao;

    @GetMapping("/tareas/tareas")
    public String mostrar(Model model) {
        model.addAttribute("tareas", new Tareas());
        return "tareas/tareas"; 
    }

   
    @PostMapping("/enviarTareas")
    public String submitForm(@ModelAttribute Tareas tareas) {
        TareasDao.save(tareas); 
        return "/tareas/gracias";
    }
}
