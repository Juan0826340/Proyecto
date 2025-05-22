package com.proyecto.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String mostrarPaginaInicio() {
        return "index"; // Nombre de la plantilla Thymeleaf (src/main/resources/templates/index.html)
    }
}