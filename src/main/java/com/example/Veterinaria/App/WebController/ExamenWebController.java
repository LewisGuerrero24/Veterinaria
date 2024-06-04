package com.example.Veterinaria.App.WebController;

import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.Examen;
import com.example.Veterinaria.App.Entity.Rol;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.EmpleadoRepository;
import com.example.Veterinaria.App.Repository.ExamenRepository;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/examenes")
public class ExamenWebController {
    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    //lista admin
    @GetMapping
    public String listarExamenes(Model model) {
        List<Examen> examenes = examenRepository.findAll();
        model.addAttribute("examenes", examenes);
        return "ListExamenes";
    }

    //lista empleados
    @GetMapping("/empleado/{idempleado}")
    public String listarExamenesEmpleado(@PathVariable("idempleado") String idempleado,Model model) {
        Optional<Usuario> user = usuarioRepository.findById(idempleado);
        Optional<Empleado> empleado = empleadoRepository.findByUser(user.get());
        List<Examen> examenes = examenRepository.findByEmpleadoResponsable(empleado.get());
        model.addAttribute("idempleado", idempleado);
        model.addAttribute("examenes", examenes);
        return "ListExamenesEmpleado";
    }

    //ListaCliente
    @GetMapping("/cliente/{idcliente}")
    public String listarExamenesCliente(@PathVariable("idcliente") String idcliente,Model model) {
        Optional<Usuario> user = usuarioRepository.findById(idcliente);
        List<Examen> examenes = examenRepository.findByUsuarioAsociado(user.get());
        model.addAttribute("idempleado", idcliente);
        model.addAttribute("examenes", examenes);
        return "ListExamenesCliente";
    }

    //Crear Admin
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("examen", new Examen());
        model.addAttribute("usuarios", usuarioRepository.findByRol(Rol.CLIENTE));
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "formExamenes";
    }

    //crear empleado
    @GetMapping("/empleado/crear/{idempleado}")
    public String mostrarFormularioCrearEmpleado(@PathVariable("idempleado")String idempleado,Model model) {

        model.addAttribute("examen", new Examen());
        model.addAttribute("idempleado", idempleado);
        model.addAttribute("usuarios", usuarioRepository.findByRol(Rol.CLIENTE));
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "formExamenesEmpleado";
    }

    //post crear Admin
    @PostMapping("/crear")
    public String crearExamen(@ModelAttribute Examen examen) {

        Usuario cliente = usuarioRepository.findById(examen.getUsuarioAsociado().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado veterinario = empleadoRepository.findById(examen.getEmpleadoResponsable().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        examen.setUsuarioAsociado(cliente);
        examen.setEmpleadoResponsable(veterinario);
        examenRepository.save(examen);
        return "redirect:/examenes";
    }



    //post crear empleado
    @PostMapping("/empleado/crea/{idempleado}")
    public String crearExamen(@PathVariable("idempleado") String idempleado,@ModelAttribute Examen examen) {


        Usuario cliente = usuarioRepository.findById(examen.getUsuarioAsociado().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado veterinario = empleadoRepository.findById(examen.getEmpleadoResponsable().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        examen.setUsuarioAsociado(cliente);
        examen.setEmpleadoResponsable(veterinario);
        examenRepository.save(examen);
        return "redirect:/examenes/empleado/"+idempleado;
    }

    //form editar administrador
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") String id, Model model) {
        Examen examen = examenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de examen inválido:" + id));
        model.addAttribute("examen", examen);
        model.addAttribute("usuarios", usuarioRepository.findByRol(Rol.CLIENTE));
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "formExamenes";
    }

    //form editar empleado
    @GetMapping("/empleado/{idempleado}/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable("idempleado") String idempleado,@PathVariable("id") String id, Model model) {
        Examen examen = examenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de examen inválido:" + id));
        model.addAttribute("idempleado", idempleado);
        model.addAttribute("examen", examen);
        model.addAttribute("usuarios", usuarioRepository.findByRol(Rol.CLIENTE));
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "formExamenesEmpleado";
    }

    //post Editar Admin
    @PostMapping("/actualizar/{id}")
    public String actualizarExamen(@PathVariable("id") String id, @ModelAttribute Examen examen) {
        examen.setId(id);
        System.out.println("Usuario Asociado ID: " + examen.getUsuarioAsociado().getId());
        System.out.println("Empleado Responsable ID: " + examen.getEmpleadoResponsable().getId());

        Usuario cliente = usuarioRepository.findById(examen.getUsuarioAsociado().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado empleado = empleadoRepository.findById(examen.getEmpleadoResponsable().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        examen.setUsuarioAsociado(cliente);
        examen.setEmpleadoResponsable(empleado);
        examenRepository.save(examen);
        return "redirect:/examenes";
    }

    //post editar empleado
    @PostMapping("/empleado/{idempleado}/actualizar/{id}")
    public String actualizarExamenEmpleado(@PathVariable("idempleado") String idempleado,@PathVariable("id") String id, @ModelAttribute Examen examen) {
        examen.setId(id);
        System.out.println("Usuario Asociado ID: " + examen.getUsuarioAsociado().getId());
        System.out.println("Empleado Responsable ID: " + examen.getEmpleadoResponsable().getId());

        Usuario cliente = usuarioRepository.findById(examen.getUsuarioAsociado().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado empleado = empleadoRepository.findById(examen.getEmpleadoResponsable().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        examen.setUsuarioAsociado(cliente);
        examen.setEmpleadoResponsable(empleado);
        examenRepository.save(examen);
        return "redirect:/examenes/empleado/"+idempleado;
    }

    //eliminar admin
    @GetMapping("/eliminar/{id}")
    public String eliminarExamen(@PathVariable("id") String id) {
        Examen examen = examenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de examen inválido:" + id));
        examenRepository.delete(examen);
        return "redirect:/examenes";
    }

    //eliminar empleado
    @GetMapping("/empleado/{idempleado}/eliminar/{id}")
    public String eliminarExamen(@PathVariable("idempleado") String idempleado,@PathVariable("id") String id) {
        Examen examen = examenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de examen inválido:" + id));
        examenRepository.delete(examen);
        return "redirect:/examenes/empleado/"+idempleado;
    }
}
