package com.example.Veterinaria.App.Repository;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Examen;
import com.example.Veterinaria.App.Entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExamenRepository extends MongoRepository<Examen, String> {
    List<Examen> findByEmpleadoResponsable(Empleado empleado);
    List<Examen> findByUsuarioAsociado(Usuario usuario);
}