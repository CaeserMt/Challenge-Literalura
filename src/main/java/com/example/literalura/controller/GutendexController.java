package com.example.literalura.controller;

import com.example.literalura.service.GutendexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GutendexController {

    private final GutendexService gutendexService;

    public GutendexController(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    @GetMapping("/buscar-libros")
    public Object buscarLibros(
            @RequestParam(required = false, defaultValue = "") String searchTerm,
            @RequestParam(required = false, defaultValue = "en") String language,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "relevance") String orderBy) { // Agregado el parámetro orderBy

        // Si no se proporciona un término de búsqueda, se devuelve un mensaje adecuado
        if (searchTerm.isEmpty()) {
            return "Por favor, ingresa un término de búsqueda.";
        }

        try {
            // Llamada al servicio para obtener los libros
            return gutendexService.buscarLibros(searchTerm, language, limit, orderBy); // Se pasa el nuevo parámetro
        } catch (Exception e) {
            // Manejo de excepciones en caso de que la API falle o el servicio tenga un problema
            return "Error al obtener los libros: " + e.getMessage();
        }
    }
}
