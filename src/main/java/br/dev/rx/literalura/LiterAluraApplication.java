import io.literatura.dto.LibroDTO;
import io.literatura.model.Autor;
import io.literatura.model.Libro;
import io.literatura.repository.LibroRepositorio;
import io.literatura.servicio.GutendexServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaAplicacion implements CommandLineRunner {
	@Autowired
	private GutendexServicio gutendexServicio;

	@Autowired
	private LibroRepositorio libroRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAplicacion.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion = -1;

		while (opcion != 0) {
			menu();
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					buscarLibroPorTitulo(scanner);
					break;

				case 2:
					listarLibros();
					break;

				case 3:
					listarAutores();
					break;

				case 4:
					buscarAutorPorNombre(scanner);
					break;

				case 5:
					listarAutoresVivosPorAnio(scanner);
					break;

				case 0:
					System.out.println("Saliendo...");
					break;

				default:
					System.out.println("Opción inválida");
			}
		}

		scanner.close();
	}

	public void menu() {
		System.out.println("1 - Buscar libro por título");
		System.out.println("2 - Listar libros guardados");
		System.out.println("3 - Listar autores guardados");
		System.out.println("4 - Buscar autor por nombre");
		System.out.println("5 - Listar autores vivos en un año específico");
		System.out.println("0 - Salir");
		System.out.println("Elija una opción:");
	}

	private void buscarLibroPorTitulo(Scanner scanner) {
		System.out.println("Ingrese el título del libro:");
		String titulo = scanner.nextLine();

		try {
			List<LibroDTO> libros = gutendexServicio.buscarLibros(titulo);

			if (libros.isEmpty()) {
				System.out.println("Ningún libro encontrado");
			} else {
				for (int i = 0; i < libros.size(); i++) {
					System.out.println(i + " - " + libros.get(i).titulo());
				}

				System.out.println("Ingrese el número del libro que desea guardar:");
				int indice = scanner.nextInt();
				scanner.nextLine();

				Libro libro = new Libro(libros.get(indice));
				libroRepositorio.save(libro);
				System.out.println("Libro guardado exitosamente!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarLibros() {
		List<Libro> libros = libroRepositorio.findAll();

		if (libros.isEmpty()) {
			System.out.println("Ningún libro guardado");
		} else {
			System.out.println("Lista de libros guardados:");
			for (Libro libro : libros) {
				System.out.println("- " + libro.getTitulo());
			}
		}
	}

	public void listarAutores() {
		List<Autor> autores = libroRepositorio.findAllAutores();

		if (autores.isEmpty()) {
			System.out.println("Ningún autor guardado");
		} else {
			System.out.println("Lista de autores guardados:");
			for (Autor autor : autores) {
				System.out.println("- " + autor.getNombre());
			}
		}
	}

	public void buscarAutorPorNombre(Scanner scanner) {
		System.out.println("Ingrese el nombre del autor:");
		String nombre = scanner.nextLine();

		List<Autor> autores = libroRepositorio.buscarPorNombreAutor(nombre);

		if (autores.isEmpty()) {
			System.out.println("Ningún autor encontrado con ese nombre");
		} else {
			System.out.println("Autores encontrados:");
			for (Autor autor : autores) {
				System.out.println("- " + autor.getNombre());
			}
		}
	}

	public void listarAutoresVivosPorAnio(Scanner scanner) {
		System.out.println("Ingrese el año:");
		int anio = scanner.nextInt();
		scanner.nextLine();

		List<Autor> autores = libroRepositorio.buscarPorAnioNacimientoMenorIgual(anio);

		if (autores.isEmpty()) {
			System.out.println("Ningún autor encontrado vivo en " + anio);
		} else {
			System.out.println("Autores vivos en " + anio + ":");
			for (Autor autor : autores) {
				System.out.println("- " + autor.getNombre());
			}
		}
	}
}