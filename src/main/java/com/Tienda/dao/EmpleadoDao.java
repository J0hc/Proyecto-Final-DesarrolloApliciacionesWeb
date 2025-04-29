
package com.Tienda.dao;

import com.Tienda.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rebec
 */

public interface EmpleadoDao extends JpaRepository<Empleado, Long> {
}