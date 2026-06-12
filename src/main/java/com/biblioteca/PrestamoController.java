package com.biblioteca;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(
            PrestamoService prestamoService) {

        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listarTodos();
    }

    @PostMapping
    public Prestamo crear(
            @RequestBody Prestamo prestamo) {

        return prestamoService.crearPrestamo(prestamo);
    }

    @PutMapping("/devolver/{id}")
    public Prestamo devolver(@PathVariable Long id) {
        return prestamoService.devolverLibro(id);
    }
}