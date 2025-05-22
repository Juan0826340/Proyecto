package com.proyecto.app.entidades;



import jakarta.persistence.*;

@Entity
@Table(name="jugadores")
public class Jugador {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String nombre;
	    private String posicion;
	    private Integer edad;
	    private String nacionalidad;
	    
	    @ManyToOne
	    private Club club;
	    
	    
	    
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
	    
	    public String getPosicion() {
	        return posicion;
	    }
	    
	    public void setPosicion(String posicion) {
	        this.posicion = posicion;
	    }
	    
	    public Integer getEdad() {
	        return edad;
	    }
	    
	    public void setEdad(Integer edad) {
	        this.edad = edad;
	    }
	    
	    public String getNacionalidad() {
	        return nacionalidad;
	    }
	    
	    public void setNacionalidad(String nacionalidad) {
	        this.nacionalidad = nacionalidad;
	    }
	    
	    public Club getClub() {
	        return club;
	    }
	    
	    public void setClub(Club club) {
	        this.club = club;
	    }
	}