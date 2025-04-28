package com.CoffeLife.service.impl;

import com.CoffeLife.dao.DescuentoDao;
import com.CoffeLife.dao.ProductoDao;
import com.CoffeLife.domain.Descuento;
import com.CoffeLife.domain.producto;
import com.CoffeLife.service.DescuentoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DescuentoServiceImpl implements DescuentoService {

    @Autowired
    private DescuentoDao descuentoDao;

    @Autowired
    private ProductoDao productoDao;

    @Override
    public void guardarDescuento(Descuento descuento) {
        producto producto = productoDao.findById(descuento.getProducto().getIdProducto()).orElse(null);
        if (producto != null) {
            double descuentoDecimal = descuento.getPorcentaje() / 100.0;
            double nuevoPrecio = producto.getPrecio() * (1 - descuentoDecimal);
            producto.setPrecio(nuevoPrecio);
            productoDao.save(producto);

            descuento.setFechaInicio(LocalDateTime.now());
            descuentoDao.save(descuento);
        }
    }

    @Override
    public List<Descuento> obtenerDescuentosActivos() {
        return descuentoDao.findAll(); 
    }

    @Override
    public void eliminarDescuento(Long id) {
        Optional<Descuento> opt = descuentoDao.findById(id);
        if (opt.isPresent()) {
            Descuento descuento = opt.get();
            producto producto = descuento.getProducto();

            descuentoDao.deleteById(id);
        }
    }

    @Override
    public Descuento buscarPorId(Long id) {
        return descuentoDao.findById(id).orElse(null);
    }
}
