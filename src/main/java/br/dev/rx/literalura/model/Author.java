package io.literatura.model;

import io.literatura.dto.AutorDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    private LocalDate añoNacimiento;
    private LocalDate añoFallecimiento;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<Libro> libros = new HashSet<>();

    public Autor() {
    }

    public Autor(String nombre, String añoNacimiento, String añoFallecimiento) {
        this.nombre = nombre;
        try {
            this.añoNacimiento = LocalDate.parse(añoNacimiento);
            this.añoFallecimiento = LocalDate.parse(añoFallecimiento);
        } catch (DateTimeParseException ex) {
            this.añoNacimiento = null;
            this.añoFallecimiento = null;
        }
    }

    @Override
    public String toString() {
        String librosStr = libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "));

        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", añoNacimiento=" + añoNacimiento +
                ", añoFallecimiento=" + añoFallecimiento +
                ", libros=" + librosStr +
                '}';
    }

    public String getNombre() {
        return nombre;
    }
}
