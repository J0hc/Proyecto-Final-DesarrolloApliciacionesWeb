package com.CoffeLife.controller;

import com.CoffeLife.dao.ProductoDao;
import com.CoffeLife.domain.Descuento;
import com.CoffeLife.domain.producto;
import com.CoffeLife.service.DescuentoService;
import com.CoffeLife.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/descuentos")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private ProductoService ps;

    @GetMapping("/verDescuentos")
    public String verDescuentos(Model model) {
        List<Descuento> descuentos = descuentoService.obtenerDescuentosActivos();
        model.addAttribute("descuento", new Descuento());
        model.addAttribute("productos", productoDao.findAll());
        model.addAttribute("descuentos", descuentoService.obtenerDescuentosActivos());
        model.addAttribute("descuentos", descuentos);

        return "/descuentos/listado";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarDescuento(@PathVariable Long id) {
        Descuento descuento = descuentoService.buscarPorId(id);

        if (descuento != null) {
            producto producto = descuento.getProducto();
            producto.setPrecio(descuento.getPrecioOriginal()); 
            ps.save(producto);
            descuentoService.eliminarDescuento(id);            
        }

        return "redirect:/descuentos/verDescuentos";
    }

    @GetMapping("/nuevo")
    public String nuevoDescuento(Model model) {
        model.addAttribute("descuento", new Descuento());
        model.addAttribute("productos", productoDao.findAll());
        return "/descuentos/verDescuentos";
    }

    @PostMapping("/guardar")
    public String guardarDescuento(@ModelAttribute Descuento descuento) {
        
        producto producto = ps.getProducto(descuento.getProducto());
        
        if(producto != null) {
             descuento.setPrecioOriginal(producto.getPrecio());
        }
        
        double nuevoPrecio = producto.getPrecio() - (producto.getPrecio() * descuento.getPorcentaje() / 100);
        producto.setPrecio(nuevoPrecio);
        
        descuento.setProducto(producto);
 
        ps.save(producto);
               
        descuentoService.guardarDescuento(descuento);
        return "redirect:/descuentos/verDescuentos";
    } 
}
