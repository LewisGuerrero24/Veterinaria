package com.example.Veterinaria.App.WebController;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Rol;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.EmpleadoRepository;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoWebController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/admin")
    public String listEmpleados(Model model) {
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "Empleados";
    }

    @GetMapping("/admin/new")
    public String newEmpleadoForm(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("usuarios", usuarioRepository.findByRol(Rol.EMPLEADO));
        return "empleadoForm";
    }

    @GetMapping("/admin/edit/{id}")
    public String editEmpleadoForm(@PathVariable String id, Model model) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            model.addAttribute("usuarios", usuarioRepository.findByRol(Rol.EMPLEADO));
            return "empleadoForm";
        } else {
            return "redirect:/empleados/admin";
        }
    }

    @PostMapping("/data")
    public String saveEmpleado(@ModelAttribute Empleado empleado) {
        // Asegurarse de que el usuario exista
        Optional<Usuario> usuario = usuarioRepository.findById(empleado.getUser().getId());
        if (usuario.isPresent()) {
            empleado.setUser(usuario.get());
            empleadoRepository.save(empleado);
            return "redirect:/empleados/admin";
        } else {
            // Manejar error si el usuario no existe
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteEmpleado(@PathVariable String id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
        }
        return "redirect:/empleados/admin";
    }
}
