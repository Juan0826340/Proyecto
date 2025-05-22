package com.proyecto.app.controlador;

import com.proyecto.app.entidades.Jugador;
import com.proyecto.app.repositorio.JugadorRepositorio;
import com.proyecto.app.repositorio.ClubRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorRepositorio jugadorRepo;
    private final ClubRepositorio clubRepo;

    public JugadorController(JugadorRepositorio jugadorRepo, ClubRepositorio clubRepo) {
        this.jugadorRepo = jugadorRepo;
        this.clubRepo = clubRepo;
    }

    @GetMapping
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        return "listJugador";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubRepo.findAll());
        return "formJugador";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorRepo.save(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepo.findById(id).orElseThrow();
        model.addAttribute("jugador", jugador);
        model.addAttribute("clubes", clubRepo.findAll());
        return "formJugador";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorRepo.deleteById(id);
        return "redirect:/jugadores";
    }

    @GetMapping("/{id}")
    public String verDetalleJugador(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepo.findById(id).orElseThrow();
        model.addAttribute("jugador", jugador);
        return "viewJugador";
    }
}