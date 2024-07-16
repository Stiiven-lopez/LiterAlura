package io.literatura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {
    private String nombre;
    @JsonAlias("birth_year")
    private String añoNacimiento;
    @JsonAlias("death_year")
    private String añoFallecimiento;

    public AutorDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(String añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public String getAñoFallecimiento() {
        return añoFallecimiento;
    }

    public void setAñoFallecimiento(String añoFallecimiento) {
        this.añoFallecimiento = añoFallecimiento;
    }
}