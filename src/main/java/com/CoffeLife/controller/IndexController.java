package com.CoffeLife.controller;

import com.CoffeLife.domain.Galeria;
import com.CoffeLife.domain.producto;
import com.CoffeLife.service.GaleriaService;
import com.CoffeLife.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    
    @Autowired
    private GaleriaService galeriaService;

    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/")
    public String index(Model model) {
   
        var imagenes = galeriaService.getGaleria(true);
        int sizeImagenes = imagenes.size();
        List<Galeria> ultimasImagenes = imagenes.subList(Math.max(sizeImagenes - 3, 0), sizeImagenes);

        var productos = productoService.getProductos(true);
        int sizeProducto = productos.size();
        List<producto> ultimosProductos = productos.subList(Math.max(sizeProducto - 3, 0), sizeProducto);

        
        model.addAttribute("ultimasImagenes", ultimasImagenes);
        model.addAttribute("ultimosProductos", ultimosProductos);

        return "index";
    }
}
