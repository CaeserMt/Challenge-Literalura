package com.example.literalura.controller;

import com.example.literalura.dto.Book;
import com.example.literalura.dto.BookResponse;
import com.example.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class BookController {

    @Autowired
    private GutendexService gutendexService;

    private Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        int opcion;

        do {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un año específico");
            System.out.println("5. Listar libros por idioma");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    System.out.println("Función de listar autores registrados aún no implementada.");
                    break;
                case 4:
                    System.out.println("Función de listar autores vivos en un año específico aún no implementada.");
                    break;
                case 5:
                    System.out.println("Función de listar libros por idioma aún no implementada.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);
    }

    public void buscarLibroPorTitulo() {
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("¿En qué idioma deseas buscar? (en, es, fr, etc.): ");
        String idioma = scanner.nextLine();
        System.out.print("¿Cuántos resultados deseas mostrar?: ");
        int limite = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        try {
            BookResponse respuesta = gutendexService.buscarLibros(titulo, idioma, limite, "popular");
            if (respuesta.getResults().isEmpty()) {
                System.out.println("No se encontraron libros con ese título.");
            } else {
                respuesta.getResults().forEach(libro -> {
                    System.out.println("\nTítulo: " + libro.getTitle());
                    System.out.println("Idioma(s): " + libro.getLanguages());
                    libro.getAuthors().forEach(autor -> System.out.println("Autor: " + autor.getName()));
                    System.out.println("-------------------------");
                });
            }
        } catch (Exception e) {
            System.err.println("Error al buscar libros: " + e.getMessage());
        }
    }

    public void listarLibrosRegistrados() {
        System.out.println("Listando libros registrados desde la API...");
        try {
            BookResponse respuesta = gutendexService.buscarLibros("", "en", 20, "popular");

            if (respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
                System.out.println("No se encontraron libros registrados.");
            } else {
                System.out.println("\nLibros registrados:");
                respuesta.getResults().forEach(libro -> {
                    System.out.println("\nTítulo: " + libro.getTitle());
                    System.out.println("Idioma(s): " + libro.getLanguages());
                    System.out.println("Descargas: " + libro.getDownload_count());
                    libro.getAuthors().forEach(autor -> System.out.println("Autor: " + autor.getName()));
                    System.out.println("-------------------------");
                });
            }
        } catch (Exception e) {
            System.err.println("Error al listar libros registrados: " + e.getMessage());
        }
    }

    public void listarLibrosPorIdioma() {
        System.out.print("Ingrese el idioma que desea buscar (ejemplo: 'en' para inglés, 'es' para español): ");
        String idioma = scanner.nextLine();

        System.out.println("Buscando libros en el idioma: " + idioma);

        try {
            // Llamamos al servicio para obtener libros filtrados por idioma
            List<Book> librosPorIdioma = gutendexService.obtenerLibrosPorIdioma(idioma);

            if (librosPorIdioma == null || librosPorIdioma.isEmpty()) {
                System.out.println("No se encontraron libros en el idioma: " + idioma);
            } else {
                System.out.println("\nLibros en el idioma '" + idioma + "':");
                for (Book libro : librosPorIdioma) {
                    System.out.println("Título: " + libro.getTitle());
                    System.out.println("Idiomas: " + libro.getLanguages());
                    System.out.println("--------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar libros por idioma: " + e.getMessage());
        }
    }
}