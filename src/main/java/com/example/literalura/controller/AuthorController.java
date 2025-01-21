package com.example.literalura.controller;

import com.example.literalura.dto.Author;
import com.example.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class AuthorController {

    @Autowired
    private GutendexService gutendexService;

    private Scanner scanner = new Scanner(System.in);

    public void listarAutoresRegistrados() {
        System.out.println("Listando autores registrados desde la API...");

        try {
            // Obtener la lista de autores únicos a través del servicio
            List<Author> autores = gutendexService.obtenerAutoresRegistrados();

            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores registrados.");
            } else {
                System.out.println("\nAutores registrados:");
                autores.forEach(autor -> {
                    System.out.println("ID: " + autor.getId());
                    System.out.println("Nombre: " + autor.getName());
                    if (autor.getBirth_year() != null) {
                        System.out.println("Año de nacimiento: " + autor.getBirth_year());
                    }
                    if (autor.getDeath_year() != null) {
                        System.out.println("Año de fallecimiento: " + autor.getDeath_year());
                    }
                    System.out.println("-------------------------");
                });
            }
        } catch (Exception e) {
            System.err.println("Error al listar autores registrados: " + e.getMessage());
        }
    }
    public void listarAutoresVivosEnAnho() {
        System.out.print("Ingresa el año para buscar autores vivos: ");
        int anho = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.println("Buscando autores vivos en el año " + anho + "...");

        try {
            List<Author> autoresVivos = gutendexService.obtenerAutoresVivosEnAnho(anho);

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anho + ".");
            } else {
                System.out.println("\nAutores vivos en el año " + anho + ":");
                autoresVivos.forEach(autor -> {
                    System.out.println("ID: " + autor.getId());
                    System.out.println("Nombre: " + autor.getName());
                    System.out.println("Año de nacimiento: " + autor.getBirth_year());
                    System.out.println("Año de fallecimiento: " + (autor.getDeath_year() != null ? autor.getDeath_year() : "N/A"));
                    System.out.println("-------------------------");
                });
            }
        } catch (Exception e) {
            System.err.println("Error al obtener autores vivos en el año " + anho + ": " + e.getMessage());
        }
    }


}