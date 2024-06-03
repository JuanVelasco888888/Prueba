package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.parcial.entity.Pelicula;
import com.app.parcial.repository.PeliculaRepositorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/pelicula")
public class PeliculasWebController {
    
    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @Autowired
    private PeliculaRepositorio peliculaRepository;
    
    @GetMapping("/lista")
    public String listPeliculas(Model model) {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        model.addAttribute("peliculas", peliculas);
        return "lista-pelicula";
    }

    @GetMapping("/crear")
    public String showNewPeliculaForm(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "formPelicula";
    }

    @PostMapping("/save")
    public String savePelicula(@ModelAttribute Pelicula pelicula, @RequestParam("imagen") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path path = Paths.get(UPLOADED_FOLDER + fileName);
                Files.copy(file.getInputStream(), path);
                pelicula.setImagenUrl("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        peliculaRepository.save(pelicula);
        return "redirect:/pelicula/lista";
    }

    @GetMapping("/edit/{id}")
    public String showEditPeliculaForm(@PathVariable String id, Model model) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pelicula Id:" + id));
        model.addAttribute("pelicula", pelicula);
        return "formPelicula";
    }

    @PostMapping("/save/{id}")
    public String updatePelicula(@PathVariable String id, @ModelAttribute("pelicula") Pelicula pelicula,
                                 @RequestParam("imagen") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path path = Paths.get(UPLOADED_FOLDER + fileName);
                Files.copy(file.getInputStream(), path);
                pelicula.setImagenUrl("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        peliculaRepository.save(pelicula);
        redirectAttributes.addFlashAttribute("message", "Película actualizada con éxito");
        return "redirect:/pelicula/lista";
    }

    @GetMapping("/delete/{id}")
    public String deletePelicula(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pelicula Id:" + id));
        peliculaRepository.delete(pelicula);
        redirectAttributes.addFlashAttribute("message", "Película eliminada con éxito");
        return "redirect:/pelicula/lista";
    }
    @GetMapping("/buscar")
    @ResponseBody
    public List<Pelicula> buscarPeliculasPorGenero(@RequestParam("genero") String genero) {
        if (genero == null || genero.isEmpty()) {
            return peliculaRepository.findAll();
        } else {
            return peliculaRepository.findByGeneroContainingIgnoreCase(genero);
        }
    }
    @GetMapping("/busqueda.html")
    public String mostrarBusqueda() {
        return "busqueda"; 
    }
}
