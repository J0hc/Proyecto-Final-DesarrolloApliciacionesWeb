package com.CoffeLife.controller;

import com.CoffeLife.domain.Galeria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.CoffeLife.dao.GaleriaDao;
import com.CoffeLife.service.FirebaseStorageService;
import com.CoffeLife.service.GaleriaService;
import com.CoffeLife.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/galeria")
public class GaleriaController {

    @Autowired
    private GaleriaDao imagenDao;
    
    @Autowired
    private GaleriaService galeriaService;

    @GetMapping("/listado")
    public String listarImagenes(Model model) {
        List<Galeria> imagenes = imagenDao.findAll();
        model.addAttribute("imagenes", imagenes);
        return "galeria/listado";
    }


    @GetMapping("/eliminar/{id}")
    public String imagenEliminar(Galeria galeria) {
        galeriaService.delete(galeria);
        return "redirect:/galeria/listado";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @PostMapping("/guardar")
    public String galeriaGuardar(Galeria galeria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {

        if (!imagenFile.isEmpty()) {
            galeria.setUrl("");
            galeriaService.save(galeria);            
            galeria.setUrl(firebaseStorageService.cargaImagen(
                    imagenFile,
                    "galeria",
                    galeria.getId()));
            }
        galeriaService.save(galeria);
         return "redirect:/galeria/listado";
    }
    
    
    @GetMapping("/mostrar")
    public String mostrar(Model model) {
        var galeria = galeriaService.getGaleria(false);
        model.addAttribute("galeria", galeria);
        return "/mostrar";
    }

}
