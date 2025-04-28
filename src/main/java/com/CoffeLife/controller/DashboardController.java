package com.CoffeLife.controller;

import com.CoffeLife.domain.Categoria;
import com.CoffeLife.domain.Contacto;
import com.CoffeLife.domain.Galeria;
import com.CoffeLife.domain.Usuario;
import com.CoffeLife.domain.producto;
import com.CoffeLife.service.CategoriaService;
import com.CoffeLife.service.ContactoService;
import com.CoffeLife.service.GaleriaService;
import com.CoffeLife.service.ProductoService;
import com.CoffeLife.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ContactoService contactoService;

    @Autowired
    private GaleriaService galeriaService;

    @GetMapping("/dashboard/dashboardAdmin")
    public String dashboardAdmin(Model model) throws JsonProcessingException {

        //Variables Generales
        var totalUsuarios = usuarioService.getUsuarios();
        var totalProductos = productoService.getProductos(true);
        var totalCategorias = categoriaService.getCategorias(true);

        //Para el total de usuarios, categorias y productos
        int sizeUsuarios = totalUsuarios.size();
        List<Usuario> ultimosUsuarios = totalUsuarios.subList(Math.max(sizeUsuarios - 3, 0), sizeUsuarios);
        int sizeProductos = totalProductos.size();
        List<producto> ultimosProductos = totalProductos.subList(Math.max(sizeProductos - 3, 0), sizeProductos);
        int sizeCategorias = totalCategorias.size();
        List<Categoria> ultimasCategorias = totalCategorias.subList(Math.max(sizeCategorias - 3, 0), sizeCategorias);

        // Totales simples para gráficas
        model.addAttribute("totalUsuariosSimple", totalUsuarios.size());
        model.addAttribute("totalProductosSimple", totalProductos.size());
        model.addAttribute("totalCategoriasSimple", totalCategorias.size());

        model.addAttribute("totalUsuarios", totalUsuarios.size());
        model.addAttribute("totalProductos", totalProductos.size());
        model.addAttribute("totalCategorias", totalCategorias.size());

        model.addAttribute("ultimosUsuarios", ultimosUsuarios);
        model.addAttribute("ultimosProductos", ultimosProductos);
        model.addAttribute("ultimasCategorias", ultimasCategorias);

        List<producto> productosBajoStock = totalProductos.stream()
                .filter(p -> p.getExistencias() <= 5)
                .collect(Collectors.toList());
        model.addAttribute("productosBajoStock", productosBajoStock);

        List<Categoria> categoriasSinProductos = totalCategorias.stream()
                .filter(categoria -> totalProductos.stream()
                .noneMatch(p -> p.getCategoria().getIdCategoria().equals(categoria.getIdCategoria())))
                .collect(Collectors.toList());
        model.addAttribute("categoriasSinProductos", categoriasSinProductos);

        List<Contacto> mensajes = contactoService.getContacto(true);
        model.addAttribute("mensajes", mensajes);

        return "dashboard/dashboardAdmin";
    }

    @GetMapping("/dashboard/dashboardVendedor")
    public String dashboardVendedor() {
        return "dashboard/dashboardVendedor";
    }

    @GetMapping("/dashboard/dashboardUser")
    public String dashboardUser(Model model) {

        var imagenes = galeriaService.getGaleria(true);
        int sizeImagenes = imagenes.size();
        List<Galeria> ultimasImagenes = imagenes.subList(Math.max(sizeImagenes - 3, 0), sizeImagenes);

        var productos = productoService.getProductos(true);
        int sizeProducto = productos.size();
        List<producto> ultimosProductos = productos.subList(Math.max(sizeProducto - 3, 0), sizeProducto);

        model.addAttribute("ultimasImagenes", ultimasImagenes);
        model.addAttribute("ultimosProductos", ultimosProductos);

        return "dashboard/dashboardUser";
    }
}
