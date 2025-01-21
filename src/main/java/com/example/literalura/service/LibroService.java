package com.example.literalura.service;

import com.example.literalura.model.Libro;
import com.example.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Método para obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();  // Utiliza el método de Spring Data JPA para obtener todos los libros
    }

    // Método para guardar un libro
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }
}
