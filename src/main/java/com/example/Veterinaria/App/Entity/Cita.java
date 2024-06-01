package com.example.Veterinaria.App.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Document(collection = "Citas")
public class Cita {

    @Id
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private LocalTime Hora;

    private Empleado veterinario;

    private Usuario cliente = null;

    // Constructor por defecto
    public Cita() {}

    // Constructor con parámetros

    public Cita(String id, LocalDate fecha, LocalTime hora, Empleado veterinario, Usuario cliente) {
        this.id = id;
        this.fecha = fecha;
        Hora = hora;
        this.veterinario = veterinario;
        this.cliente = cliente;
    }


    // Getters y Setters


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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
}
