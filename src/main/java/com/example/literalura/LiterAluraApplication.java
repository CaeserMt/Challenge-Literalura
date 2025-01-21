package com.example.literalura;
import java.util.List;
import java.util.Scanner;
import com.example.literalura.controller.AuthorController;
import com.example.literalura.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookController bookController;

	@Autowired
	private AuthorController authorController;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		startMenu();
	}

	private void startMenu() {
		int opcion;
		Scanner scanner = new Scanner(System.in);

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
					bookController.buscarLibroPorTitulo();
					break;
				case 2:
					bookController.listarLibrosRegistrados();
					break;
				case 3:
					authorController.listarAutoresRegistrados();
					break;
				case 4:
					authorController.listarAutoresVivosEnAnho(); //
					break;
				case 5:
					bookController.listarLibrosPorIdioma();
					break;
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción inválida. Inténtalo de nuevo.");
			}
		} while (opcion != 0);

		scanner.close();
	}
}