package com.app.parcial.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;
    private String idUsuario;
    private String idPelicula;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private String hora;
    private List<Integer> numeroAsientos; // Lista de asientos reservados

    // Constructor vacío
    public Reserva() {
    }

    // Constructor con parámetros
    public Reserva(String idUsuario, String idPelicula, Date fecha, String hora, List<Integer> numeroAsientos) {
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroAsientos = numeroAsientos;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Integer> getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(List<Integer> numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }
}
