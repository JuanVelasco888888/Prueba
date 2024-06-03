package com.app.parcial.controller;

import com.app.parcial.repository.ReservasRepositorio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.parcial.entity.Reserva;
import com.app.parcial.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/reserva")
public class ReservasRestController {
    
    @Autowired
    private ReservasRepositorio res_repositorio;

    @GetMapping("/")
    public List<Reserva> getAllReserva() {
        return res_repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable String id) {
        return res_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Reservas no encontrada"));
    }

    @PostMapping("/")
    public Reserva saveReserva(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Reserva reserva = mapper.convertValue(body, Reserva.class);
        return res_repositorio.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Reserva reserva = mapper.convertValue(body, Reserva.class);
        reserva.setId(id);
        return res_repositorio.save(reserva);
    }

    @DeleteMapping("/{id}")
    public Reserva deleteReserva(@PathVariable String id) {
        Reserva reserva = res_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Reservas no encontrada"));
        res_repositorio.deleteById(id);
        return reserva;
    }
}
