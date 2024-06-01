package com.example.Veterinaria.App.Controller;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.EmpleadoRepository;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable String id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        // Asegurarse de que el usuario exista
        Optional<Usuario> usuario = usuarioRepository.findById(empleado.getUser().getId());
        if (usuario.isPresent()) {
            empleado.setUser(usuario.get());
            return empleadoRepository.save(empleado);
        } else {
            // Manejar error si el usuario no existe
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable String id, @RequestBody Empleado empleadoDetails) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            Empleado empleadoToUpdate = empleado.get();
            empleadoToUpdate.setCargo(empleadoDetails.getCargo());
            empleadoToUpdate.setFechaContratacion(empleadoDetails.getFechaContratacion());
            empleadoToUpdate.setHorarioTrabajo(empleadoDetails.getHorarioTrabajo());
            empleadoToUpdate.setEspecializacion(empleadoDetails.getEspecializacion());
            empleadoToUpdate.setUser(empleadoDetails.getUser());
            return ResponseEntity.ok(empleadoRepository.save(empleadoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable String id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
