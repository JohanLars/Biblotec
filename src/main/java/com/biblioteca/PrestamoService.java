package com.biblioteca;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public PrestamoService(
            PrestamoRepository prestamoRepository,
            LibroRepository libroRepository) {

        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll();
    }

    public Prestamo crearPrestamo(Prestamo prestamo) {

        // Buscar el libro en la base de datos por su ID
        Long libroId = prestamo.getLibro().getId();

        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() ->
                        new RuntimeException("Libro no encontrado"));

        // Validar disponibilidad
        if (libro.getCantidadDisponible() <= 0) {
            throw new RuntimeException("No hay ejemplares disponibles");
        }

        // Descontar una unidad del inventario
        libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
        libroRepository.save(libro);

        // Asignar el libro obtenido de la base de datos
        prestamo.setLibro(libro);

        // Registrar fechas y estado
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaVencimiento(LocalDate.now().plusDays(7));
        prestamo.setEstado("ACTIVO");

        // Guardar el préstamo
        return prestamoRepository.save(prestamo);
    }

    public Prestamo devolverLibro(Long prestamoId) {

        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() ->
                        new RuntimeException("Préstamo no encontrado"));

        Libro libro = prestamo.getLibro();

        // Devolver una unidad al inventario
        libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
        libroRepository.save(libro);

        // Actualizar el préstamo
        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.setEstado("DEVUELTO");

        return prestamoRepository.save(prestamo);
    }
}