package com.proyecto.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.app.entidades.Competicion;

public interface CompeticionRepositorio extends JpaRepository<Competicion, Long> {

}
