package com.example.literalura;

import com.example.literalura.model.Autor;
import com.example.literalura.service.AutorService;
import com.example.literalura.service.GutendexService;  // Importar el servicio de Gutendex
import com.example.literalura.dto.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    @Autowired
    private AutorService autorService;

    @Autowired
    private GutendexService gutendexService; // Asegúrate de inyectar el servicio de Gutendex

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un año específico");
            System.out.println("5. Listar libros por idioma");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            int opcion = obtenerOpcionValida(scanner);

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo(scanner);
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio(scanner);
                    break;
                case 5:
                    listarLibrosPorIdioma(scanner);
                    break;
                case 0:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private int obtenerOpcionValida(Scanner scanner) {
        int opcion = -1;
        while (opcion < 0 || opcion > 5) {
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
        return opcion;
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("El título no puede estar vacío.");
            return;
        }

        System.out.print("¿En qué idioma deseas buscar? (en, es, fr, etc.): ");
        String idioma = scanner.nextLine();

        System.out.print("¿Cuántos resultados deseas mostrar?: ");
        int limit = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        try {
            BookResponse respuesta = gutendexService.buscarLibros(titulo, idioma, limit, "popular");
            if (respuesta != null && !respuesta.getResults().isEmpty()) {
                System.out.println("Libros encontrados:");
                respuesta.getResults().forEach(libro -> System.out.println(libro.getTitle()));
            } else {
                System.out.println("No se encontraron libros con el título '" + titulo + "'.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar libros: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        // Asumiendo que existe un servicio para listar libros registrados
        // List<Libro> libros = libroService.obtenerTodosLosLibros();
        // Si la base de datos está bien configurada, puedes obtener los libros desde allí.
        System.out.println("Función de listar libros registrados aún no implementada.");
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.obtenerTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            autores.forEach(autor -> System.out.println(autor.getNombre()));
        }
    }

    private void listarAutoresVivosEnAnio(Scanner scanner) {
        System.out.print("Ingresa el año para buscar autores vivos: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Llamar a un servicio para obtener autores vivos en un año específico
        // Asumir que AutorService tiene un método para eso
        List<Autor> autoresVivos = autorService.obtenerAutoresVivosEnAnio(anio);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en " + anio + ":");
            autoresVivos.forEach(autor -> System.out.println(autor.getNombre()));
        }
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingresa el idioma (en, es, fr, etc.): ");
        String idioma = scanner.nextLine();

        try {
            // Llamada al servicio de Gutendex para obtener libros por idioma
            BookResponse respuesta = gutendexService.buscarLibros("", idioma, 10, "popular");
            if (respuesta != null && !respuesta.getResults().isEmpty()) {
                System.out.println("Libros encontrados en idioma '" + idioma + "':");
                respuesta.getResults().forEach(libro -> System.out.println(libro.getTitle()));
            } else {
                System.out.println("No se encontraron libros en el idioma '" + idioma + "'.");
            }
        } catch (Exception e) {
            System.out.println("Error al listar libros por idioma: " + e.getMessage());
        }
    }
}
