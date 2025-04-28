package com.CoffeLife.controller;

import com.CoffeLife.domain.producto;
import com.CoffeLife.domain.Categoria;
import com.CoffeLife.service.CategoriaService;
import com.CoffeLife.service.ProductoService;
import com.CoffeLife.service.impl.FirebaseStorageServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());

        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(@PathVariable("idCategoria") Long idCategoria, Model model) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria); 

        List<producto> productos = productoService.getProductos(true);

        if (productos == null) {
            productos = new ArrayList<>();
        }
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());

        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());

        return "/pruebas/listado2";
    }

    @PostMapping("/query1")
    public String consultaQuery1(
            @RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup,
            Model model) {
        var productos = productoService.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());

        model.addAttribute("precioInferior", precioInf);
        model.addAttribute("precioSuperior", precioSup);

        return "/pruebas/listado2";
    }

    @PostMapping("/query2")
    public String consultaQuery2(
            @RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup,
            Model model) {
        var productos = productoService.metodoJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());

        model.addAttribute("precioInferior", precioInf);
        model.addAttribute("precioSuperior", precioSup);

        return "/pruebas/listado2";
    }

    @PostMapping("/query3")
    public String consultaQuery3(
            @RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup,
            Model model) {
        var productos = productoService.metodoSQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());

        model.addAttribute("precioInferior", precioInf);
        model.addAttribute("precioSuperior", precioSup);

        return "/pruebas/listado2";
    }
}
