package com.CoffeLife.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".firebasestorage.com"
    final String BucketName = "tienda-c1a8d"+".firebasestorage.app";

    //Esta es la ruta básica de este proyecto Techshop
    final String rutaSuperiorStorage = "techshop";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    final String archivoJsonFile = "tienda-c1a8d-firebase-adminsdk-fbsvc-12569b2deb"+".json";
}

