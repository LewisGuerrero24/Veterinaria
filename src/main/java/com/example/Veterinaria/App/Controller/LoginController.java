package com.example.Veterinaria.App.Controller;


import com.example.Veterinaria.App.Entity.Rol;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        return "Login";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/nosotros")
    public String Nosotros(Model model) {
        return "Nosotros";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getPassword().equals(password)) {

            if (usuario.getRol() == Rol.EMPLEADO) {

                return "redirect:/usuarios/Empleado/view/"+usuario.getId();
            } else if (usuario.getRol() == Rol.CLIENTE) {

                return "redirect:/usuarios/cliente/view/"+usuario.getId();
            } else if (usuario.getRol() == Rol.ADMINISTRADOR) {

            return "redirect:/usuarios/admin/view";
        }else {

                return "redirect:/";
            }
        }else{
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}

