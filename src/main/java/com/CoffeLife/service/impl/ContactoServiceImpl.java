package com.CoffeLife.service.impl;

import com.CoffeLife.dao.ContactoDao;
import com.CoffeLife.domain.Contacto;
import com.CoffeLife.service.ContactoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactoServiceImpl implements ContactoService {
@Autowired
    private ContactoDao contactoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getContacto(boolean activos) {
        var lista = contactoDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Contacto getContacto(Contacto contacto) {
        return contactoDao.findById(contacto.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Contacto contacto) {
        contactoDao.save(contacto);
    }

    @Override
    @Transactional
    public void delete(Contacto contacto) {
        contactoDao.delete(contacto);
    }
}
