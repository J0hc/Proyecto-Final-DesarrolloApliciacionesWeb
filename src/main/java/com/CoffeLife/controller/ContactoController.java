package com.CoffeLife.controller;

import com.CoffeLife.dao.ContactoDao;
import com.CoffeLife.domain.Contacto;
import com.CoffeLife.service.ContactoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @Autowired
    private ContactoDao ContactoDao;

    @GetMapping("/contacto")
    public String mostrar(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "contacto/contacto";
    }

    @PostMapping("/contacto/enviarContacto")
    public String submitForm(@ModelAttribute Contacto contacto) {
        ContactoDao.save(contacto);
        return "contacto/gracias";
    }

    @Autowired
    private ContactoService contactoService;

   @GetMapping("/eliminar/{id}")
    public String imagenEliminar(Contacto contacto) {
        contactoService.delete(contacto);
        return "redirect:/dashboard/dashboardAdmin";
    }
}
