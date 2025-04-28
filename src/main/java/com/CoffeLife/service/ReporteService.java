package com.CoffeLife.service;

import jakarta.mail.Quota.Resource;
import java.io.IOException;
import java.util.Map;
import org.springframework.http.ResponseEntity;
public interface ReporteService {
    public ResponseEntity<org.springframework.core.io.Resource> generaReporte(
            String reporte,
            Map<String, Object> parametros,
            String tipo
    ) 
            throws IOException;
}
