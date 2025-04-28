package com.CoffeLife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/info/quienesSomos")
    public String quienesSomos(Model model) {
        return "/info/quienesSomos";
    }
    
    @GetMapping("/info/preguntasYRespuestas")
    public String preguntasYRespuestas(Model model) {
        return "/info/preguntasYRespuestas";
    }
    
    @GetMapping("/info/dondeEstamos")
    public String dondeEstamos(Model model) {
        return "/info/dondeEstamos";
    }   
}
