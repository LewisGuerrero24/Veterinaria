package com.example.Veterinaria.App.Dto;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Usuario;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class DTOCitaSinUser {
    @Id
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private LocalTime Hora;

    private Empleado veterinario;

    public DTOCitaSinUser() {
    }

    public DTOCitaSinUser(String id, LocalDate fecha, LocalTime hora, Empleado veterinario) {
        this.id = id;
        this.fecha = fecha;
        Hora = hora;
        this.veterinario = veterinario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return Hora;
    }

    public void setHora(LocalTime hora) {
        Hora = hora;
    }

    public Empleado getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Empleado veterinario) {
        this.veterinario = veterinario;
    }
}
