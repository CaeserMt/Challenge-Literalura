package com.example.literalura.controller;

import com.example.literalura.model.Autor;
import com.example.literalura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutorController {

    @Autowired
    private AutorService autorService;

    // Método para listar todos los autores
    @GetMapping("/autores")
    public List<Autor> obtenerTodosLosAutores() {
        return autorService.obtenerTodosLosAutores();
    }

    // Método para obtener autores vivos en un año específico
    @GetMapping("/autores/vivos")
    public List<Autor> obtenerAutoresVivosEnAnio(@RequestParam int anio) {
        return autorService.obtenerAutoresVivosEnAnio(anio);
    }

    // Método para obtener autores por idioma
    @GetMapping("/autores/idioma")
    public List<Autor> obtenerAutoresPorIdioma(@RequestParam String idioma) {
        return autorService.obtenerAutoresPorIdioma(idioma);
    }
}
