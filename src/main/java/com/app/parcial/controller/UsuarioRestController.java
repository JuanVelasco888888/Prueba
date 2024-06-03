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

import com.app.parcial.entity.Usuario;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.UsuarioRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioRestController {
	
	@Autowired
	private UsuarioRepositorio usRepositorio;
	
	@GetMapping("/")
	public List<Usuario> getAllUsuarios(){
		return usRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario getUsuarioById(@PathVariable String id) {
		return usRepositorio.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
	}
	
	@PostMapping("/")
	public Usuario saveUsuario(@RequestBody Map<String, Object>body) {
		ObjectMapper mapper = new ObjectMapper();
		Usuario usuario = mapper.convertValue(body, Usuario.class);
		return usRepositorio.save(usuario);
	}
	
	@PutMapping("/{id}")
	public Usuario updateUsuario(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Usuario usuario = mapper.convertValue(body, Usuario.class);
		usuario.setId(id);
		return usRepositorio.save(usuario);
	}
	
	@DeleteMapping("/{id}")
	public Usuario deleteUsuario(@PathVariable String id) {
		Usuario usuario = usRepositorio.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
		usRepositorio.deleteById(id);
		return usuario;
	}
	

}
