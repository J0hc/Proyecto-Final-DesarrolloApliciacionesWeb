package com.Tienda.controller;

import com.Tienda.domain.CuentaUsuario;
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.Tienda.service.CuentaUsuarioService;
import com.google.cloud.firestore.Blob;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.core.Path;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
@RequestMapping("/cuentaUsuario")
public class CuentaUsuarioController {

    @Autowired
    private CuentaUsuarioService cuentaUsuarioService;

    /*
    @GetMapping("/index_cuenta")
    public String inicio(Model model) {
        var cuentaUsuarios = cuentaUsuarioService.getCuentaUsuarios(false);
        model.addAttribute("cuentaUsuarios", cuentaUsuarios);
        model.addAttribute("totalCuentaUsuarios", cuentaUsuarios.size());
        return "/cuentaUsuario/index_cuenta";
    }*/
    @GetMapping("/index_cuenta")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cuentaUsuario", new CuentaUsuario());
        return "cuentaUsuario/index_cuenta";
    }

    @GetMapping("/nuevo")
    public String cuentaUsuarioNuevo(CuentaUsuario cuentaUsuario) {
        return "/cuentaUsuario/index_cuenta";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @PostMapping("/guardar")
    public String cuentaUsuarioGuardar(CuentaUsuario cuentaUsuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        cuentaUsuarioService.save(cuentaUsuario);
        if (!imagenFile.isEmpty()) {
            cuentaUsuario.setImagenUrl(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "cuentaUsuario",
                            cuentaUsuario.getIdCuentaUsuario()));
        }
        cuentaUsuarioService.save(cuentaUsuario);
        return "/cuentaUsuario/cuentaCreada";
    }

    @GetMapping("/eliminar/{idCuentaUsuario}")
    public String cuentaUsuarioEliminar(CuentaUsuario cuentaUsuario) {
        cuentaUsuarioService.delete(cuentaUsuario);
        return "redirect:/cuentaUsuario/index_cuenta";
    }

    @GetMapping("/modificar/{idCuentaUsuario}")
    public String cuentaUsuarioModificar(CuentaUsuario cuentaUsuario, Model model) {
        cuentaUsuario = cuentaUsuarioService.getCuentaUsuario(cuentaUsuario);
        model.addAttribute("cuentaUsuario", cuentaUsuario);
        return "/cuentaUsuario/index_cuenta";
    }
}
