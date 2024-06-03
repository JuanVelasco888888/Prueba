package com.app.parcial.controller;

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

import com.app.parcial.entity.Sala;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.SalaRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/sala")
public class SalaRestController {
    
    @Autowired
    private SalaRepositorio sala_repositorio;

    @GetMapping("/")
    public List<Sala> getAllSalas() {
        return sala_repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Sala getSalaById(@PathVariable String id) {
        return sala_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Sala no encontrada"));
    }

    @PostMapping("/")
    public Sala saveSala(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Sala sala = mapper.convertValue(body, Sala.class);
        return sala_repositorio.save(sala);
    }

    @PutMapping("/{id}/marcarOcupados")
    public Sala marcarAsientosOcupados(@PathVariable String id, @RequestBody List<Integer> asientosOcupados) {
        Sala sala = sala_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Sala no encontrada"));
        sala.setAsientosOcupados(asientosOcupados);
        return sala_repositorio.save(sala);
    }

    @DeleteMapping("/{id}")
    public Sala deleteSala(@PathVariable String id) {
    	Sala sala= sala_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Horario no encontrada"));
        sala_repositorio.deleteById(id);
        return sala;
    }
}

