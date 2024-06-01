package com.example.Veterinaria.App.WebController;

import com.example.Veterinaria.App.Entity.Cita;
import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Rol;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.CitaRepository;
import com.example.Veterinaria.App.Repository.EmpleadoRepository;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitaWebController {
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/admin")
    public String listCitas(Model model) {
        model.addAttribute("citas", citaRepository.findAll());
        return "Citas";
    }

    @GetMapping("/empleado/{empleadoId}")
    public String listCitasByEmpleado(@PathVariable("empleadoId") String empleadoId, Model model) {
        Optional<Usuario> usuario = usuarioRepository.findById(empleadoId);
        Optional<Empleado> empleado = empleadoRepository.findByUser(usuario.get());
        if (empleado.isPresent()) {
            List<Cita> citas = citaRepository.findByVeterinarioOrderByFechaAsc(empleado.get());
            model.addAttribute("IdEmpleado", empleadoId);
            model.addAttribute("citas", citas);
            return "CitasEmpleado";
        } else {
            return "redirect:/";
        }
    }
    @GetMapping("/Usuario/{usuarioId}")
    public String listCitasByUsuario(@PathVariable("usuarioId") String usuarioId, Model model) {
        model.addAttribute("UsuarioId", usuarioId);
        model.addAttribute("citas", citaRepository.findAll());
        return "CitasUsuario";
    }

    @GetMapping("/MisCitas/{usuarioId}")
    public String listCitasByUsuarioMy(@PathVariable("usuarioId") String usuarioId, Model model) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        model.addAttribute("UsuarioId", usuarioId);
        model.addAttribute("citas", citaRepository.findByCliente(usuario.get()));
        return "MisCitasUsuario";
    }

    @GetMapping("/admin/new")
    public String newCitaForm(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("veterinarios", empleadoRepository.findAll());
        model.addAttribute("clientes", usuarioRepository.findByRol(Rol.CLIENTE));
        return "citaForm";
    }

    @GetMapping("/admin/edit/{id}")
    public String editCitaForm(@PathVariable String id, Model model) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            model.addAttribute("cita", cita.get());
            model.addAttribute("veterinarios", empleadoRepository.findAll());
            model.addAttribute("clientes",usuarioRepository.findByRol(Rol.CLIENTE));
            return "citaForm";
        } else {
            return "redirect:/citas/admin";
        }
    }

    @PostMapping
    public String saveCita(@ModelAttribute Cita cita) {
        // Asegurarse de que el veterinario y el cliente existan
        Optional<Empleado> veterinario = empleadoRepository.findById(cita.getVeterinario().getId());
        Optional<Usuario> cliente = usuarioRepository.findById(cita.getCliente().getId());
        if (veterinario.isEmpty() || cliente.isEmpty()) {
            throw new RuntimeException("Veterinario o cliente no encontrado");
        }
        cita.setVeterinario(veterinario.get());
        cita.setCliente(cliente.get());
        citaRepository.save(cita);
        return "redirect:/citas/admin";
    }

    @PostMapping("/seleccionarCliente/{id}")
    public String seleccionarCliente(@PathVariable("id")String id,@RequestParam("citaId") String citaId) {
        Optional<Cita> citaOptional = citaRepository.findById(citaId);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();

            Optional<Usuario> user = usuarioRepository.findById(id);
            // Actualizar el campo "cliente" con el usuario actual
            cita.setCliente(user.get());
            citaRepository.save(cita);
        }
        return "redirect:/citas/Usuario/"+id; // Redirigir a la página de listado de citas
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteCita(@PathVariable String id) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            citaRepository.delete(cita.get());
        }
        return "redirect:/citas/admin";
    }
}

