package com.example.Veterinaria.App.Controller;

import com.example.Veterinaria.App.Entity.Cita;
import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.CitaRepository;
import com.example.Veterinaria.App.Repository.EmpleadoRepository;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable String id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cita createCita(@RequestBody Cita cita) {
        // Asegurarse de que el veterinario y el cliente existan
        Optional<Empleado> veterinario = empleadoRepository.findById(cita.getVeterinario().getUser().getId());
        Optional<Usuario> cliente = usuarioRepository.findById(cita.getCliente().getId());

        if (veterinario.isPresent() && cliente.isPresent()) {
            cita.setVeterinario(veterinario.get());
            cita.setCliente(cliente.get());
            return citaRepository.save(cita);
        } else {
            // Manejar error si el veterinario o el cliente no existen
            throw new RuntimeException("Veterinario o cliente no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable String id, @RequestBody Cita citaDetails) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            Cita citaToUpdate = cita.get();
            citaToUpdate.setFecha(citaDetails.getFecha());
            citaToUpdate.setHora(citaDetails.getHora());
            citaToUpdate.setVeterinario(citaDetails.getVeterinario());
            citaToUpdate.setCliente(citaDetails.getCliente());
            return ResponseEntity.ok(citaRepository.save(citaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable String id) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            citaRepository.delete(cita.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}