package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.parcial.entity.Pelicula;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.PeliculaRepositorio;

@Controller
public class HomeController {
    @Autowired
    private PeliculaRepositorio pel_repositorio;

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("peliculas", pel_repositorio.findAll());
        return "index";
    }

    @GetMapping("/index2")
    public String home2(Model model) {
        model.addAttribute("peliculas", pel_repositorio.findAll());
        return "index2";
    }
    @GetMapping("/pelicula/{id}")
    public String peliculaDetalle(@PathVariable String id, Model model) {
        Pelicula pelicula = pel_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Pelicula no encontrada"));
        model.addAttribute("pelicula", pelicula);
        return "detalles-pelicula";
    }
}