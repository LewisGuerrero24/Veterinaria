package com.example.Veterinaria.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "HistoriasClinicas")
public class HistoriaClinica {

    @Id
    private String id;

    private Usuario cliente;

    private Empleado veterinario;

    private Cita cita;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String descripcion;

    private String tratamientos;

    private String observaciones;

    // Constructor por defecto
    public HistoriaClinica() {}

    // Constructor con parámetros


    public HistoriaClinica(String id, Usuario cliente, Empleado veterinario, Cita cita, LocalDate fecha, String descripcion, String tratamientos, String observaciones) {
        this.id = id;
        this.cliente = cliente;
        this.veterinario = veterinario;
        this.cita = cita;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tratamientos = tratamientos;
        this.observaciones = observaciones;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Empleado getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Empleado veterinario) {
        this.veterinario = veterinario;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
