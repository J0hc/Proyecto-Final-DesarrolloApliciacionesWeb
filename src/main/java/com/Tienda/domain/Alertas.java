package com.Tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="alertas")
public class Alertas implements Serializable {
    
    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_alertas")
    private Long idAlertas;
    private String tituloAlerta;
    private String descripcion;
   
    public Alertas() {
    }

    public Long getIdAlertas() {
        return idAlertas;
    }

    public void setIdAlertas(Long idAlertas) {
        this.idAlertas = idAlertas;
    }

    public String getTituloAlerta() {
        return tituloAlerta;
    }

    public void setTituloAlerta(String tituloAlerta) {
        this.tituloAlerta = tituloAlerta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
 