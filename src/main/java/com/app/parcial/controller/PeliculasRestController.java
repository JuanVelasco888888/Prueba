package com.app.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.parcial.entity.Pelicula;
import com.app.parcial.repository.PeliculaRepositorio;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasRestController {

    @Autowired
    private PeliculaRepositorio peliculaRepository;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> getPeliculaById(@PathVariable String id) {
        return peliculaRepository.findById(id);
    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula updatePelicula(@PathVariable String id, @RequestBody Pelicula peliculaDetails) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow();
        pelicula.setTitulo(peliculaDetails.getTitulo());
        pelicula.setDirector(peliculaDetails.getDirector());
        pelicula.setGenero(peliculaDetails.getGenero());
        pelicula.setDuracion(peliculaDetails.getDuracion());
        pelicula.setClasificacion(peliculaDetails.getClasificacion());
        pelicula.setSinopsis(peliculaDetails.getSinopsis());
        pelicula.setHorario(peliculaDetails.getHorario());
        pelicula.setFecha(peliculaDetails.getFecha());
        pelicula.setImagenUrl(peliculaDetails.getImagenUrl());
        return peliculaRepository.save(pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable String id) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow();
        peliculaRepository.delete(pelicula);
    }
}
