package com.example.Veterinaria.App.Repository;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmpleadoRepository extends MongoRepository<Empleado, String> {


    Optional<Empleado> findByUser(Usuario user);
}
