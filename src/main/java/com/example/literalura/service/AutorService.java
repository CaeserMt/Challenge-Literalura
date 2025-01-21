package com.example.literalura.service;

import com.example.literalura.model.Autor;
import com.example.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // Método para obtener todos los autores
    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    // Método para obtener autores vivos en un año específico
    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    // Método para obtener autores por idioma
    public List<Autor> obtenerAutoresPorIdioma(String idioma) {
        return autorRepository.findByIdioma(idioma);  // Cambio al nombre del método corregido
    }
}
