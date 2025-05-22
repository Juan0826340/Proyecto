package com.proyecto.app.entidades;


import java.util.ArrayList;

import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name="clubes")
public class Club {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String nombre;
	    
	    @ManyToOne
	    private Asociacion asociacion;

	    @OneToOne(orphanRemoval = false)  // Evita eliminación en cascada
	    private Entrenador entrenador;

	    @OneToMany(orphanRemoval = false)
	    @JoinColumn(name = "id_club")
	    private List<Jugador> jugadores = new ArrayList<>();

	    @ManyToMany
	    @JoinTable(
	        name = "club_competicion",
	        joinColumns = @JoinColumn(name = "club_id"),
	        inverseJoinColumns = @JoinColumn(name = "competicion_id")
	    )
	    private List<Competicion> competiciones = new ArrayList<>();
	    
	    // Getters y Setters
	    public Long getId() {
	        return id;
	    }
	    
	    public void setId(Long id) {
	        this.id = id;
	    }
	    
	    public String getNombre() {
	        return nombre;
	    }
	    
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    
	    public Asociacion getAsociacion() {
	        return asociacion;
	    }
	    
	    public void setAsociacion(Asociacion asociacion) {
	        this.asociacion = asociacion;
	    }
	    
	    public Entrenador getEntrenador() {
	        return entrenador;
	    }
	    
	    public void setEntrenador(Entrenador entrenador) {
	        this.entrenador = entrenador;
	    }
	    
	    public List<Jugador> getJugadores() {
	        return jugadores;
	    }
	    
	    public void setJugadores(List<Jugador> jugadores) {
	        this.jugadores = jugadores;
	    }
	    
	    public List<Competicion> getCompeticiones() {
	        return competiciones;
	    }
	    
	    public void setCompeticiones(List<Competicion> competiciones) {
	        this.competiciones = competiciones;
	    }
	    
	    // Métodos helper para relación ManyToMany con Competicion
	    public void addCompeticion(Competicion competicion) {
	        this.competiciones.add(competicion);
	        competicion.getClubes().add(this);
	    }
	    
	    public void removeCompeticion(Competicion competicion) {
	        this.competiciones.remove(competicion);
	        competicion.getClubes().remove(this);
	    }
	}