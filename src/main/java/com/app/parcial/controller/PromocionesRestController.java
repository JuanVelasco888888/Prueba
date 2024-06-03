package com.app.parcial.controller;

import com.app.parcial.entity.Promociones;
import com.app.parcial.repository.PromocionesRepositorio;

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

import com.app.parcial.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/promociones")
public class PromocionesRestController {
    
@Autowired
    private PromocionesRepositorio pro_repositorio;

    @GetMapping("/")
    public List<Promociones> getAllPromociones() {
        return  pro_repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Promociones getPromocionesById(@PathVariable String id) {
        return  pro_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Promociones no encontrada"));
    }

    @PostMapping("/")
    public Promociones savePromociones(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Promociones promociones= mapper.convertValue(body, Promociones.class);
        return  pro_repositorio.save(promociones);
    }

    @PutMapping("/{id}")
    public Promociones updatePromociones(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Promociones promociones= mapper.convertValue(body, Promociones.class);
        promociones.setId(id);
        return  pro_repositorio.save(promociones);
    }

    @DeleteMapping("/{id}")
    public Promociones deletePromociones(@PathVariable String id) {
    	Promociones promociones=  pro_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Promociones no encontrado"));
    	 pro_repositorio.deleteById(id);
        return promociones;
    }
}
