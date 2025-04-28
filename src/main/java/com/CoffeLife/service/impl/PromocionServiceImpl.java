package com.CoffeLife.service.impl;

import com.CoffeLife.dao.PromocionDao;
import com.CoffeLife.domain.Promocion;
import com.CoffeLife.service.PromocionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromocionServiceImpl  implements PromocionService{

    @Autowired
    private PromocionDao promocionDao;

    @Override
    @Transactional(readOnly = true) 
    public List<Promocion> listarPromociones() {
        return promocionDao.findAll();
    }

    @Override
    public void guardarPromocion(Promocion promocion) {
        promocionDao.save(promocion);
    }
    
    @Override
    public Promocion guardar(Promocion promocion) {
        promocionDao.save(promocion);
        return promocion;
    }

    @Override
    public void eliminarPromocion(Long id) {
        promocionDao.deleteById(id);
    }

    @Override
    public Promocion obtenerPromocionPorId(Long id) {
        return promocionDao.findById(id).orElse(null);
    }
}

