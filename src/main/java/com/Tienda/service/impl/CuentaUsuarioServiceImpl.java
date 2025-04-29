/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.domain.CuentaUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Tienda.dao.CuentaUsuarioDao;
import com.Tienda.service.CuentaUsuarioService;

@Service
public class CuentaUsuarioServiceImpl implements CuentaUsuarioService {

    @Autowired
    private CuentaUsuarioDao cuentaUsuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<CuentaUsuario> getCuentaUsuarios(boolean activos) {
        var lista = cuentaUsuarioDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaUsuario getCuentaUsuario(CuentaUsuario cuentaUsuario) {
        return cuentaUsuarioDao.findById(cuentaUsuario.getIdCuentaUsuario()).orElse(null);
    }

    @Override
    @Transactional
    public void save(CuentaUsuario cuentaUsuario) {
        cuentaUsuarioDao.save(cuentaUsuario);
    }

    @Override
    @Transactional
    public void delete(CuentaUsuario cuentaUsuario) {
        cuentaUsuarioDao.delete(cuentaUsuario);
    }
}
