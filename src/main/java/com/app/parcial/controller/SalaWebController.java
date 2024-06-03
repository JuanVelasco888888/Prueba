package com.app.parcial.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.parcial.entity.Sala;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.SalaRepositorio;

	@Controller
	@RequestMapping(value = "sala")
	public class SalaWebController {
		
		@Autowired
		private SalaRepositorio sala_repositorio;
		
		@GetMapping("/lista")
		public String salaListTemplate(Model model) {
			List<Sala> salas = sala_repositorio.findAll();
			salas.forEach(sala -> {
			    String selectedSitioStr = sala.getSelectedSitio() != null ? sala.getSelectedSitio().stream()
			                                       .map(String::valueOf)
			                                       .collect(Collectors.joining(", ")) : "";
			    sala.setSelectedSitioStr(selectedSitioStr);
			});
		    model.addAttribute("salas", salas);
		    return "lista-sala";
		}

		
		@GetMapping("/crear")
		public String salaNewTemplate(Model model) {
			model.addAttribute("sala", new Sala());
			return "formSala";
		}
		
		@GetMapping("/edit/{id}")
		public String salaEditTemplate(@PathVariable("id") String id, Model model) {
			model.addAttribute("sala", sala_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Sala no encontrada")));
			return "formSala";
		}
		
		@PostMapping("/save")
		public String SalaSaveProcess(@ModelAttribute("sala") Sala sala) {
			if(sala.getId().isEmpty()) {
				sala.setId(null);
			}
			sala_repositorio.save(sala);
			return "redirect:/sala/lista";
		}
		
		@GetMapping("/delete/{id}")
		public String salaDeleteProcess(@PathVariable("id") String id) {
			sala_repositorio.deleteById(id);
			return "redirect:/sala/lista";
			
		}

	}

