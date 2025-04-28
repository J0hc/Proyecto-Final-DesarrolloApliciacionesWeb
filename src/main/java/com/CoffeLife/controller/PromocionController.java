package com.CoffeLife.controller;

import com.CoffeLife.domain.Promocion;
import com.CoffeLife.domain.producto;
import com.CoffeLife.service.FirebaseStorageService;
import com.CoffeLife.service.ProductoService;
import com.CoffeLife.service.PromocionService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;
    @Autowired
    private ProductoService productoService;

    @GetMapping({"/listado", ""})
    public String verPromociones(Model model) {
        model.addAttribute("promociones", promocionService.listarPromociones());
        model.addAttribute("promocion", new Promocion());
        model.addAttribute("productos", productoService.getProductos(true));
        return "/promociones/listado";
    }

    @PostMapping("/guardar")
    public String guardarPromocion(
            Promocion promocion,
            @RequestParam("productosSeleccionados") List<Long> productosSeleccionados,
            @RequestParam("archivoImagen") MultipartFile imagenFile) {

        List<producto> lista = productoService.listarProductosPorIds(productosSeleccionados);
        promocion.setProductos(lista);

        promocionService.guardarPromocion(promocion);

        if (!imagenFile.isEmpty()) {
            promocion.setImagenUrl(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "promociones",
                            promocion.getId()));
        }

        promocionService.guardarPromocion(promocion);

        return "redirect:/promociones/listado";
    }


    @PostMapping("/eliminar/{id}")
    public String eliminarPromocion(@PathVariable Long id) {
        promocionService.eliminarPromocion(id);
        return "redirect:/promociones/listado";
    }

    @GetMapping("/promocionesUser")
    public String verPromocionesParaUsuario(Model model) {
        List<Promocion> lista = promocionService.listarPromociones();
        model.addAttribute("promociones", lista);
        return "/promociones/promocionesUser";
    }
}
