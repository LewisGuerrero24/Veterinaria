package com.example.Veterinaria.App.Entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "Empleados")
public class Empleado  {

    @Id
    private String id;
    private String cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaContratacion;
    private String horarioTrabajo;
    private String especializacion;

    private Usuario user;

    // Constructor con parámetros

    public Empleado() {
    }

    public Empleado(String id, String cargo, LocalDate fechaContratacion, String horarioTrabajo, String especializacion, Usuario user) {
        this.id = id;
        this.cargo = cargo;
        this.fechaContratacion = fechaContratacion;
        this.horarioTrabajo = horarioTrabajo;
        this.especializacion = especializacion;
        this.user = user;
    }

    // Getters y Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}

