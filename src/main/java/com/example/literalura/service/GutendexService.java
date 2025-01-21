package com.example.literalura.service;

import com.example.literalura.dto.Author;
import com.example.literalura.dto.Book;
import com.example.literalura.dto.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    // Cambiado a la URL correcta del endpoint
    private static final String API_URL = "https://gutendex.com/books/";

    // Método para buscar libros con los parámetros de búsqueda
    public BookResponse buscarLibros(String searchTerm, String language, int limit, String sort) {
        // Construir la URL de la API con los parámetros proporcionados
        String url = String.format("%s?search=%s&languages=%s&limit=%d&sort=%s",
                API_URL,
                searchTerm != null ? searchTerm : "", // Manejo de nulidad en searchTerm
                language,
                limit,
                sort);

        // Crear una instancia de RestTemplate para realizar la solicitud HTTP
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Realizar la solicitud GET a la API y convertir la respuesta a un objeto BookResponse
            return restTemplate.getForObject(url, BookResponse.class);
        } catch (Exception e) {
            // Manejo de excepciones si la API no responde o se produce un error
            throw new RuntimeException("Error al obtener los libros desde la API de Gutendex: " + e.getMessage());
        }
    }
    public List<Author> obtenerAutoresRegistrados() throws Exception {
        // Realizar una llamada para obtener libros desde la API
        BookResponse respuesta = buscarLibros("", "en", 100, "popular"); // Ajusta si necesitas más resultados o diferentes parámetros

        // Extraer los autores únicos de la lista de libros
        return respuesta.getResults().stream()
                .flatMap(libro -> libro.getAuthors().stream()) // Obtener la lista de autores de cada libro
                .distinct() // Filtrar autores únicos
                .collect(Collectors.toList());
    }
    public List<Author> obtenerAutoresVivosEnAnho(int anho) throws Exception {
        // Obtener todos los autores registrados
        List<Author> autoresRegistrados = obtenerAutoresRegistrados();

        // Filtrar autores vivos en el año especificado
        return autoresRegistrados.stream()
                .filter(autor -> autor.getBirth_year() != null && autor.getBirth_year() <= anho) // Nacidos antes o en el año
                .filter(autor -> autor.getDeath_year() == null || autor.getDeath_year() > anho) // No han fallecido o fallecieron después del año
                .collect(Collectors.toList());
    }

    public List<Book> obtenerLibrosPorIdioma(String idioma) throws Exception {
        // Obtenemos un listado general de libros desde la API
        BookResponse respuesta = buscarLibros("", idioma, 100, "popular");

        // Filtramos los libros para asegurarnos de que están en el idioma especificado
        return respuesta.getResults().stream()
                .filter(libro -> libro.getLanguages() != null && libro.getLanguages().contains(idioma))
                .collect(Collectors.toList());
    }

}