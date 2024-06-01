package com.example.Veterinaria.App.WebController;

import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class WebControllerUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "RegistroClientes";
    }

    @GetMapping("/registroUser")
    public String showRegistrationUserForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "AdminRegistroUsuarios";
    }

    @GetMapping("/cliente/view/{id}")
    public String ViewUsuario(@PathVariable("id") String id, Model model) {
        model.addAttribute("IdUsuario", id);
        return "MenuUsuarios";
    }

    @GetMapping("/Empleado/view/{id}")
    public String ViewEmpleado(@PathVariable("id") String id, Model model) {
        model.addAttribute("IdEmpleado",id);
        return "ViewEmpleado";
    }

    @GetMapping("/admin/view")
    public String ViewAdmin( Model model) {
        List<Usuario> user = usuarioRepository.findAll();
        model.addAttribute("usuarios",user);
        return "AdminView";
    }
}
