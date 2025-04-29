/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.dao.ContactoDao;
import com.Tienda.domain.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ContactoController {

    @Autowired
    private ContactoDao ContactoDao;

    @GetMapping("/contacto/contacto")
    public String mostrar(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "contacto/contacto"; 
    }

   
    @PostMapping("/enviarContacto")
    public String submitForm(@ModelAttribute Contacto contacto) {
        ContactoDao.save(contacto); 
        return "/contacto/gracias";
    }
}
