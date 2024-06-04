package com.example.Veterinaria.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "Examenes")
public class Examen {

    @Id
    private String id;
    private String nombreMascota;
    private String tipoExamen;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaExamen;
    private String resultado;
    private Empleado empleadoResponsable;
    private Usuario usuarioAsociado;

    public Examen() {
    }

    public Examen(String id, String nombreMascota, String tipoExamen, LocalDate fechaExamen, String resultado, Empleado empleadoResponsable, Usuario usuarioAsociado) {
        this.id = id;
        this.nombreMascota = nombreMascota;
        this.tipoExamen = tipoExamen;
        this.fechaExamen = fechaExamen;
        this.resultado = resultado;
        this.empleadoResponsable = empleadoResponsable;
        this.usuarioAsociado = usuarioAsociado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public LocalDate getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(LocalDate fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Empleado getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Empleado empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }
}
