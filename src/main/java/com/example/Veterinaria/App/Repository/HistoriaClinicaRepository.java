package com.example.Veterinaria.App.Repository;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.HistoriaClinica;
import com.example.Veterinaria.App.Entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoriaClinicaRepository extends MongoRepository<HistoriaClinica, String> {
    List<HistoriaClinica> findByVeterinario(Empleado veterinario);
    List <HistoriaClinica> findByCliente(Usuario cliente);
}

