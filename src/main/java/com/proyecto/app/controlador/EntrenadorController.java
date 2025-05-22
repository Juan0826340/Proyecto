package com.proyecto.app.controlador;

import com.proyecto.app.entidades.Entrenador;
import com.proyecto.app.repositorio.EntrenadorRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {

    private final EntrenadorRepositorio entrenadorRepo;

    public EntrenadorController(EntrenadorRepositorio entrenadorRepo) {
        this.entrenadorRepo = entrenadorRepo;
    }

    // Listar todos los entrenadores
    @GetMapping
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        return "listEntrenador";
    }

    // Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "formEntrenador";
    }

    // Guardar (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorRepo.save(entrenador);
        return "redirect:/entrenadores";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("entrenador", entrenadorRepo.findById(id).orElseThrow());
        return "formEntrenador";
    }

    // Eliminar un entrenador
    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorRepo.deleteById(id);
        return "redirect:/entrenadores";
    }

    // Ver detalles de un entrenador
    @GetMapping("/{id}")
    public String verEntrenador(@PathVariable Long id, Model model) {
        model.addAttribute("entrenador", entrenadorRepo.findById(id).orElseThrow());
        return "viewEntrenador";
    }
}