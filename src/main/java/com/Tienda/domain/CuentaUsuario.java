package com.Tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Entity
@Table(name="usuario")
public class CuentaUsuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")

    private Long idUsuario;
    private String nombre;
    private String username;
    private String password;
    private String apellidos;
    private String correo;
    private String telefono;
    private String ruta_imagen;
    private boolean activo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    @Transient
    private transient MultipartFile imagenFile;
    
   
    
    /* @OneToMany
    @Column(name="id_cuentaUsuario")    
    private List<producto> productos;*/
    
    
    public CuentaUsuario() {
    }
    
    public MultipartFile getImagenFile() {
        return imagenFile;
    }

    public void setImagenFile(MultipartFile imagenFile) {
        this.imagenFile = imagenFile;
    }

    public Long getIdCuentaUsuario() {
        return idUsuario;
    }
    
     public void setImagenUrl(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
    
    public String getImagenUrl() {
        return ruta_imagen;
    }
    public void setIdCuentaUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    

   public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
/* 
   public CuentaUsuario(String cuentaUsuario, boolean activo) {
        this.descripcion = cuentaUsuario;
        this.activo = activo;
    }*/

}
 