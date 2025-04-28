package com.CoffeLife.service;

import com.CoffeLife.domain.producto;
import java.util.List;

public interface ProductoService {
    
    public List<producto> getProductos(boolean activos);
    
   public producto getProducto(producto producto);
    
    public void save(producto producto);
    
    public void delete(producto producto);
    
    public List<producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    public List<producto> metodoJPQL(double precioInf, double precioSup);

    public List<producto> metodoSQL(double precioInf, double precioSup);

    public List<producto> listarProductosPorIds(List<Long> ids);
}