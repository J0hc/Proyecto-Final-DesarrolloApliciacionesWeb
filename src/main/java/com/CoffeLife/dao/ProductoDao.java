package com.CoffeLife.dao;


import com.CoffeLife.domain.producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository <producto,Long> {
    
    public List<producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    @Query(value="SELECT p FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.descripcion ASC")
    public List<producto> metodoJPQL(double precioInf, double precioSup);


    @Query(nativeQuery=true, value="SELECT * FROM producto WHERE precio BETWEEN :precioInf AND :precioSup ORDER BY descripcion ASC")
    public List<producto> metodoSQL(double precioInf, double precioSup);
}