/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.dao;

import com.Tienda.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Julián HC
 */

public interface ContactoDao extends JpaRepository<Contacto, Long> {
}