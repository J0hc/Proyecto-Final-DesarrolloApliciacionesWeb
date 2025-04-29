
package com.Tienda.dao;

import com.Tienda.domain.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rebec
 */

public interface TareasDao extends JpaRepository<Tareas, Long> {
}