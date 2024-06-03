package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.parcial.entity.Reserva;
import com.app.parcial.entity.Usuario;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.ReservasRepositorio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reserva")
public class ReservasWebController {

    @Autowired
    private ReservasRepositorio res_repositorio;

    @GetMapping("/lista")
    public String reservaListTemplate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario); // Agregar el usuario al modelo
        model.addAttribute("reservas", res_repositorio.findAll()); // Pasar las reservas al modelo
        return "lista-reserva";
    }



    @GetMapping("/crear")
    public String reservaNewTemplate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Manejar el caso en que el usuario no esté autenticado
            return "redirect:/login"; // Por ejemplo, redirigir a la página de inicio de sesión
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("reserva", new Reserva());
        return "formReservas";
    }

    @GetMapping("/edit/{id}")
    public String reservaEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("reserva", res_repositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva no encontrada")));
        return "formReservas";
    }

    @PostMapping("/save")
    public String peliculaSaveProcess(@ModelAttribute("reserva") Reserva reserva)  {
        if (reserva.getId() == null || reserva.getId().isEmpty()) {
            reserva.setId(null);
        }

        res_repositorio.save(reserva);
        return "redirect:/reserva/lista";
    }

    @GetMapping("/delete/{id}")
    public String reservaDeleteProcess(@PathVariable("id") String id) {
        res_repositorio.deleteById(id);
        return "redirect:/reserva/lista";
    }
}
