package com.Tienda.controller;

import com.Tienda.dao.AlertasDao;
import com.Tienda.domain.Alertas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AlertasController {

    @Autowired
    private AlertasDao AlertasDao;

    @GetMapping("/alertas/alertas")
    public String mostrar(Model model) {
        model.addAttribute("alertas", new Alertas());
        return "alertas/alertas"; 
    }

   
    @PostMapping("/enviarAlerta")
    public String submitForm(@ModelAttribute Alertas alertas) {
        AlertasDao.save(alertas); 
        return "/alertas/gracias";
    }
}
