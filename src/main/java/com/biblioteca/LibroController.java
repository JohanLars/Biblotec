package com.biblioteca;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {

        System.out.println("🔥 LIBRO RECIBIDO: " + libro.getTitulo());

        libro.setActivo(true);

        return libroRepository.save(libro);
    }
}