package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.parcial.entity.Administrador;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.AdministradoresRepositorio;

@Controller
@RequestMapping(value = "administradores")
public class AdministradorWebController {
	
	@Autowired
	private AdministradoresRepositorio adRep;
	
	@GetMapping("/lista")
	public String administradorListTemplate(Model model) {
		model.addAttribute("administrador", adRep.findAll());
		return "lista-Administradores";
	}
	
	@GetMapping("/crear")
	public String administradorNewTemplate(Model model) {
		model.addAttribute("administradores", new Administrador());
		return "formAdministradores";
	}
	
	@GetMapping("/edit/{id}")
	public String administradorEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("administradores", adRep.findById(id).orElseThrow(()-> new NotFoundException("Administrador no encontrado")));
		return "formAdministradores";
	}
	
	@PostMapping("/save")
	public String administradorSaveProcess(@ModelAttribute("administradores")Administrador administrador) {
		if(administrador.getId().isEmpty()) {
			administrador.setId(null);
		}
		adRep.save(administrador);
		return "redirect:/administradores/lista";
	}
	
	@GetMapping("/delete/{id}")
	public String administradorDeleteProcess(@PathVariable("id")String id) {
		adRep.deleteById(id);
		return "redirect:/administradores/lista";
	}
}
