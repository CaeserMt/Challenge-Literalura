package com.example.literalura.controller;

import com.example.literalura.model.Libro;
import com.example.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Método para obtener todos los libros
    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    // Método para agregar un nuevo libro
    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }
}
