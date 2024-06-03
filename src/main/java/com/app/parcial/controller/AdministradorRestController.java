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

import com.app.parcial.entity.Administrador;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.AdministradoresRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value ="/api/Administrador")
public class AdministradorRestController {
	
	@Autowired
	private AdministradoresRepositorio adRep;
	
	@GetMapping("/")
	public List<Administrador> getAllAdministrador(){
		return adRep.findAll();
	}
	
	@GetMapping("/{id}")
	public Administrador getAdministradorById(@PathVariable String id) {
		return adRep.findById(id).orElseThrow(() -> new NotFoundException("Administrador no encontrado"));
	}
	
	@PostMapping("/")
	public Administrador saveAdministrador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Administrador administrador = mapper.convertValue(body, Administrador.class);
		return adRep.save(administrador);
	}
	
	@PutMapping("{id}")
	public Administrador updateAdministrador(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Administrador administrador = mapper.convertValue(body, Administrador.class);
		administrador.setId(id);
		return adRep.save(administrador);
	}
	
	@DeleteMapping("/{id}")
	public Administrador deleteAdministrador(@PathVariable String id) {
		Administrador administrador = adRep.findById(id).orElseThrow(()-> new NotFoundException("Administrador no encontrado"));
		adRep.deleteById(id);
		return administrador;
	}
	
	

}
