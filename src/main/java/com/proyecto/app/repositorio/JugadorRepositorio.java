package com.proyecto.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.app.entidades.Jugador;

public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {

}
