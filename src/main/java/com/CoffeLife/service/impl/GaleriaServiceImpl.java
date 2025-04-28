package com.CoffeLife.service.impl;

import com.CoffeLife.dao.CategoriaDao;
import com.CoffeLife.dao.GaleriaDao;
import com.CoffeLife.domain.Categoria;
import com.CoffeLife.domain.Galeria;
import com.CoffeLife.service.CategoriaService;
import com.CoffeLife.service.GaleriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GaleriaServiceImpl implements GaleriaService {

    @Autowired
    private GaleriaDao galeriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Galeria> getGaleria(boolean activos) {
        var lista = galeriaDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Galeria getGaleria(Galeria galeria) {
        return galeriaDao.findById(galeria.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Galeria galeria) {
        galeriaDao.save(galeria);
    }

    @Override
    @Transactional
    public void delete(Galeria galeria) {
        galeriaDao.delete(galeria);
    }
}
