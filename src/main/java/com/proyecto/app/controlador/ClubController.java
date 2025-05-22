package com.proyecto.app.controlador;

import com.proyecto.app.entidades.*;
import com.proyecto.app.repositorio.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clubes")
public class ClubController {
    
    private final ClubRepositorio clubRepo;
    private final AsociacionRepositorio asociacionRepo;
    private final EntrenadorRepositorio entrenadorRepo;
    private final CompeticionRepositorio competicionRepo;
    
    public ClubController(ClubRepositorio clubRepo, 
                        AsociacionRepositorio asociacionRepo,
                        EntrenadorRepositorio entrenadorRepo,
                        CompeticionRepositorio competicionRepo) {
        this.clubRepo = clubRepo;
        this.asociacionRepo = asociacionRepo;
        this.entrenadorRepo = entrenadorRepo;
        this.competicionRepo=competicionRepo;
    }
    
    @GetMapping
    public String listarClubes(Model model) {
        model.addAttribute("clubes", clubRepo.findAll());
        return "listClub";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll()); // Añadir esta línea
        return "formClub";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Club club = clubRepo.findById(id).orElseThrow();
        model.addAttribute("club", club);
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll()); // Añadir esta línea
        model.addAttribute("competicionesSeleccionadas", 
            club.getCompeticiones().stream().map(Competicion::getId).collect(Collectors.toList()));
        return "formClub";
    }

    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club, 
                            @RequestParam(required = false) List<Long> competicionIds) {
        
        if (competicionIds != null) {
            List<Competicion> competiciones = competicionRepo.findAllById(competicionIds);
            club.setCompeticiones(competiciones);
        } else {
            club.setCompeticiones(new ArrayList<>());
        }
        
        clubRepo.save(club);
        return "redirect:/clubes";
    }
    

    
    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        clubRepo.deleteById(id);
        return "redirect:/clubes";
    }
    
    @GetMapping("/{id}")
    public String verDetalleClub(@PathVariable Long id, Model model) {
        Club club = clubRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Club no encontrado"));
        model.addAttribute("club", club);
        return "viewClub";
    }
}