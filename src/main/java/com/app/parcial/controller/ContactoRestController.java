
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

import com.app.parcial.entity.Contacto;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.ContactoRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/contactos")
public class ContactoRestController {
    
    @Autowired
    private ContactoRepositorio cot_repositorio;

    @GetMapping("/")
    public List<Contacto> getAllContacto() {
        return cot_repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Contacto getContactoById(@PathVariable String id) {
        return cot_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Contacto no encontrada"));
    }

    @PostMapping("/")
    public Contacto saveContacto(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Contacto contacto = mapper.convertValue(body, Contacto.class);
        return cot_repositorio.save(contacto);
    }

    @PutMapping("/{id}")
    public Contacto updateContacto(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Contacto contacto= mapper.convertValue(body, Contacto.class);
        contacto.setId(id);
        return cot_repositorio.save(contacto);
    }

    @DeleteMapping("/{id}")
    public Contacto deleteHContacto(@PathVariable String id) {
    	Contacto contacto= cot_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Contacto no encontrado"));
        cot_repositorio.deleteById(id);
        return contacto;
    }
}
