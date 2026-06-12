package com.biblioteca;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByEstado(String estado);

    List<Prestamo> findByUsuarioId(Long usuarioId);
}