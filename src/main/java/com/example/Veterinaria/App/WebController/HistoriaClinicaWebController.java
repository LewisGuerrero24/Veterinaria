package com.example.Veterinaria.App.WebController;
import com.example.Veterinaria.App.Entity.Empleado;
import com.example.Veterinaria.App.Entity.HistoriaClinica;
import com.example.Veterinaria.App.Entity.Rol;
import com.example.Veterinaria.App.Entity.Usuario;
import com.example.Veterinaria.App.Repository.EmpleadoRepository;
import com.example.Veterinaria.App.Repository.HistoriaClinicaRepository;
import com.example.Veterinaria.App.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/historias-clinicas")
public class HistoriaClinicaWebController {
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public String listarHistoriasClinicas(Model model) {
        List<HistoriaClinica> historiasClinicas = historiaClinicaRepository.findAll();
        model.addAttribute("historiasClinicas", historiasClinicas);
        return "ListHistoriaClinica";
    }

    //Empleado Listar
    @GetMapping("/empleado/{id}")
    public String listarHistoriasClinicasEmpleados(@PathVariable("id") String id,Model model) {
        System.out.println("mi Id es = "+id);
        Optional<Usuario> userEmpleado = usuarioRepository.findById(id);
        System.out.println(userEmpleado.get());
        Optional<Empleado> empleado  = empleadoRepository.findByUser(userEmpleado.get());
        System.out.println(empleado.get());
        List<HistoriaClinica> historiasClinicas = historiaClinicaRepository.findByVeterinario(empleado.get());
        model.addAttribute("idempleado", id);
        model.addAttribute("historiasClinicas", historiasClinicas);
        return "ListEmpleadoHistoriaClinica";
    }

    //ListarCliente
    @GetMapping("/cliente/{id}")
    public String listarHistoriasClinicasCliente(@PathVariable("id")String id,Model model) {
        Optional<Usuario> userEmpleado = usuarioRepository.findById(id);
        List<HistoriaClinica> historiasClinicas = historiaClinicaRepository.findByCliente(userEmpleado.get());
        model.addAttribute("idusuario", id);
        model.addAttribute("historiasClinicas", historiasClinicas);
        return "ListClienteHistoriaClinica";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("historiaClinica", new HistoriaClinica());
        model.addAttribute("clientes", usuarioRepository.findByRol(Rol.CLIENTE));
        model.addAttribute("veterinarios", empleadoRepository.findAll());
        return "formHistoriaClinica";
    }

    //Empleado Crear
    @GetMapping("/empleado/crear/{id}")
    public String mostrarFormularioCrearEmpleado(@PathVariable("id") String id,Model model) {
        model.addAttribute("idempleado", id);
        model.addAttribute("historiaClinica", new HistoriaClinica());
        model.addAttribute("clientes", usuarioRepository.findByRol(Rol.CLIENTE));
        model.addAttribute("veterinarios", empleadoRepository.findAll());
        return "formEmpleadoHistoriaClinica";
    }

  //Admin
    @PostMapping
    public String crearHistoriaClinica(@ModelAttribute HistoriaClinica historiaClinica) {
        Usuario cliente = usuarioRepository.findById(historiaClinica.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado veterinario = empleadoRepository.findById(historiaClinica.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        historiaClinica.setCliente(cliente);
        historiaClinica.setVeterinario(veterinario);

        historiaClinicaRepository.save(historiaClinica);
        return "redirect:/historias-clinicas";
    }

    //Empleado metodo crear Post
    @PostMapping("/empleado/{idempleado}")
    public String crearHistoriaClinicaEmpleado(@PathVariable("idempleado") String idempleado,@ModelAttribute HistoriaClinica historiaClinica) {
        Usuario cliente = usuarioRepository.findById(historiaClinica.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado veterinario = empleadoRepository.findById(historiaClinica.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        historiaClinica.setCliente(cliente);
        historiaClinica.setVeterinario(veterinario);

        historiaClinicaRepository.save(historiaClinica);
        return "redirect:/historias-clinicas/empleado/"+idempleado;
    }

    //formulario editar administrador
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") String id, Model model) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(id).orElse(null);
        if (historiaClinica != null) {
            model.addAttribute("historiaClinica", historiaClinica);
            model.addAttribute("clientes", usuarioRepository.findAll());
            model.addAttribute("veterinarios", empleadoRepository.findAll());
            return "formHistoriaClinica";
        } else {
            return "redirect:/historias-clinicas";
        }
    }

    //Formulario editar empleado
    @GetMapping("/empleado/{idempleado}/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable("idempleado") String idempleado,@PathVariable("id") String id, Model model) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(id).orElse(null);
        if (historiaClinica != null) {
            model.addAttribute("idempleado",idempleado);
            model.addAttribute("historiaClinica", historiaClinica);
            model.addAttribute("clientes", usuarioRepository.findAll());
            model.addAttribute("veterinarios", empleadoRepository.findAll());
            return "formHistoriaClinica";
        } else {
            return "redirect:/historias-clinicas/empleado/"+idempleado;
        }
    }

    //metodo post editar empleado
    @PostMapping("/actualizar/{id}/{idempleado}")
    public String actualizarHistoriaClinicaEmpleado(@PathVariable("idempleado") String idempleado,@PathVariable("id") String id, @ModelAttribute HistoriaClinica historiaClinica) {
        historiaClinica.setId(id);
        Usuario cliente = usuarioRepository.findById(historiaClinica.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado veterinario = empleadoRepository.findById(historiaClinica.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        historiaClinica.setCliente(cliente);
        historiaClinica.setVeterinario(veterinario);
        historiaClinicaRepository.save(historiaClinica);
        return "redirect:/historias-clinicas/empleado/"+idempleado;
    }

    //metodo post editar administrador
    @PostMapping("/actualizar/{id}")
    public String actualizarHistoriaClinica(@PathVariable("id") String id, @ModelAttribute HistoriaClinica historiaClinica) {
        historiaClinica.setId(id);
        Usuario cliente = usuarioRepository.findById(historiaClinica.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado veterinario = empleadoRepository.findById(historiaClinica.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        historiaClinica.setCliente(cliente);
        historiaClinica.setVeterinario(veterinario);
        historiaClinicaRepository.save(historiaClinica);
        return "redirect:/historias-clinicas";
    }

    //Metodo  eliminar del administrador
    @GetMapping("/eliminar/{id}")
    public String eliminarHistoriaClinica(@PathVariable("id") String id) {
        historiaClinicaRepository.deleteById(id);
        return "redirect:/historias-clinicas";
    }


    //metodo eliminar del empleado
    @GetMapping("/empleado/{idempleado}/eliminar/{id}")
    public String eliminarHistoriaClinicaEmpleado(@PathVariable("idempleado") String idempleado, @PathVariable("id") String id) {
        historiaClinicaRepository.deleteById(id);
        return "redirect:/historias-clinicas/empleado/"+idempleado;
    }
}
