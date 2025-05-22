package com.proyecto.app.controlador;

import com.proyecto.app.entidades.Competicion;
import com.proyecto.app.entidades.Club;
import com.proyecto.app.repositorio.CompeticionRepositorio;
import com.proyecto.app.repositorio.ClubRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {

    private final CompeticionRepositorio competicionRepo;
    private final ClubRepositorio clubRepo;

    public CompeticionController(CompeticionRepositorio competicionRepo, ClubRepositorio clubRepo) {
        this.competicionRepo = competicionRepo;
        this.clubRepo = clubRepo;
    }

    // Listar todas las competiciones
    @GetMapping
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "listCompeticion";
    }

    // Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("clubes", clubRepo.findAll());
        return "formCompeticion";
    }

    // Guardar (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarCompeticion(
        @ModelAttribute Competicion competicion,
        @RequestParam(required = false) List<Long> clubIds) {

        if (clubIds != null) {
            List<Club> clubesSeleccionados = clubRepo.findAllById(clubIds);
            competicion.setClubes(clubesSeleccionados);
        }
        competicionRepo.save(competicion);
        return "redirect:/competiciones";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Competicion competicion = competicionRepo.findById(id).orElseThrow();
        model.addAttribute("competicion", competicion);
        model.addAttribute("clubes", clubRepo.findAll());
        model.addAttribute("clubesSeleccionados", competicion.getClubes().stream().map(Club::getId).toList());
        return "formCompeticion";
    }

    // Eliminar una competición
    @GetMapping("/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionRepo.deleteById(id);
        return "redirect:/competiciones";
    }

    // Ver detalles de una competición
    @GetMapping("/{id}")
    public String verCompeticion(@PathVariable Long id, Model model) {
        Competicion competicion = competicionRepo.findById(id).orElseThrow();
        model.addAttribute("competicion", competicion);
        return "viewCompeticion";
    }
}