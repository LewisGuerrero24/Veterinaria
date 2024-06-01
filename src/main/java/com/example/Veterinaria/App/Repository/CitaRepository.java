package com.example.Veterinaria.App.Repository;

import com.example.Veterinaria.App.Entity.Cita;
import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitaRepository extends MongoRepository<Cita, String> {
    List<Cita> findByVeterinarioOrderByFechaAsc(Empleado veterinario);
    List<Cita> findByCliente(Usuario user);
}