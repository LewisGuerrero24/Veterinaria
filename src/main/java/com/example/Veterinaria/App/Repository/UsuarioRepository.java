package com.example.Veterinaria.App.Repository;

import com.example.Veterinaria.App.Entity.Rol;
import com.example.Veterinaria.App.Entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByEmail(String email);
    List<Usuario> findByRol(Rol rol);
}
