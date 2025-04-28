package com.CoffeLife.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Table(name = "descuento")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double porcentaje;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private double precioOriginal;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private producto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }

    @Transient
    public String getTiempoRestante() {
        if (fechaFin == null) {
            return "N/A";
        }
        Duration duracion = Duration.between(LocalDateTime.now(), fechaFin);
        long dias = duracion.toDays();
        long horas = duracion.toHoursPart();
        long minutos = duracion.toMinutesPart();
        return dias + "d " + horas + "h " + minutos + "min";
    }
}
