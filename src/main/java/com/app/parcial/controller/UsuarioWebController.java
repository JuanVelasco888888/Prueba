package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.parcial.entity.Usuario;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.UsuarioRepositorio;

@Controller
@RequestMapping(value = "usuarios")
public class UsuarioWebController {
	
	@Autowired
	private UsuarioRepositorio usRepositorio;
	
	@GetMapping("/lista")
	public String usuariosListTemplate(Model model) {
		model.addAttribute("usuario", usRepositorio.findAll());
		return "lista-usuarios";
	}
	
	@GetMapping("/crear")
	public String usuarioNewTemplate(Model model) {
		model.addAttribute("usuarios", new Usuario());
		return "formUsuarios";
	}
	
	@GetMapping("/edit/{id}")
	public String usuariosEditTemplate(@PathVariable("id")String id, Model model) {
		model.addAttribute("usuarios", usRepositorio.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado")));
		return "formUsuarios";
	}
	
	@PostMapping("/save")
	public String usuariosSaveProcess(@ModelAttribute("usuarios")Usuario usuario) {
		if(usuario.getId().isEmpty()) {
			usuario.setId(null);
		}
		usRepositorio.save(usuario);
		return "redirect:/usuario/login";
	}
	
	@GetMapping("/delete/{id}")
	public String usuariosDeleteProcess(@PathVariable("id")String id) {
		usRepositorio.deleteById(id);
		return "redirect:/usuarios/lista";
	}

}
