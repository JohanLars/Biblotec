package com.biblioteca;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> listar() {
        return autorService.listarTodos();
    }

    @PostMapping
    public Autor guardar(@RequestBody Autor autor) {
        return autorService.guardar(autor);
    }
}