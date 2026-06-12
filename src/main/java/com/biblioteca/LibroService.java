package com.biblioteca;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public Libro guardar(Libro libro) {
        libro.setActivo(true); // importante
        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, Libro libroActualizado) {

        Libro libro = buscarPorId(id);

        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setCategoria(libroActualizado.getCategoria());
        libro.setCantidadDisponible(libroActualizado.getCantidadDisponible());

        return libroRepository.save(libro);
    }

    public void desactivar(Long id) {

        Libro libro = buscarPorId(id);
        libro.setActivo(false);

        libroRepository.save(libro);
    }
}