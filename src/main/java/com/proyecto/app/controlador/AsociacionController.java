package com.proyecto.app.controlador;

import com.proyecto.app.entidades.Asociacion;
import com.proyecto.app.repositorio.AsociacionRepositorio;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {

    private final AsociacionRepositorio asociacionRepo;

    public AsociacionController(AsociacionRepositorio asociacionRepo) {
        this.asociacionRepo = asociacionRepo;
    }

    @GetMapping
    public String listarAsociaciones(Model model) {
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        return "listAsociacion";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "formAsociacion";
    }

    @PostMapping("/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        asociacionRepo.save(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Asociacion asociacion = asociacionRepo.findById(id).orElseThrow();
        model.addAttribute("asociacion", asociacion);
        return "formAsociacion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionRepo.deleteById(id);
        return "redirect:/asociaciones";
    }

    @GetMapping("/{id}")
    public String verAsociacion(@PathVariable Long id, Model model) {
        Asociacion asociacion = asociacionRepo.findById(id).orElseThrow();
        Hibernate.initialize(asociacion.getClubes()); // Inicializa la relación LAZY
        model.addAttribute("asociacion", asociacion);
        return "viewAsociacion";
    }
}