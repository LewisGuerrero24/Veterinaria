package com.example.Veterinaria.App.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "Citas")
public class Cita {

    @Id
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private LocalTime hora;

    private Empleado veterinario;

    private Usuario cliente;

    private TipoCitas tipoCitas;

    // Constructor por defecto
    public Cita() {}

    // Constructor con parámetros
    public Cita(String id, LocalDate fecha, LocalTime hora, Empleado veterinario, Usuario cliente, TipoCitas tipoCitas) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.veterinario = veterinario;
        this.cliente = cliente;
        this.tipoCitas = tipoCitas;
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
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public TipoCitas getTipoCitas() {
        return tipoCitas;
    }

    public void setTipoCitas(TipoCitas tipoCitas) {
        this.tipoCitas = tipoCitas;
    }
}
