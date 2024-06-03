package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.parcial.entity.Promociones;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.PromocionesRepositorio;

@Controller
	@RequestMapping(value = "promociones")
	public class PromocionesWebController {
		
		@Autowired
		private PromocionesRepositorio pro_repositorio;
		
		@GetMapping("/lista")
		public String PromocioneListTemplate(Model model) {
			model.addAttribute("Promociones", pro_repositorio.findAll());
			return "lista-promociones";
		}
		
		@GetMapping("/crear")
		public String PromocioneNewTemplate(Model model) {
			model.addAttribute("Promociones", new Promociones());
			return "Promocion";
		}
		
		@GetMapping("/edit/{id}")
		public String contactoEditTemplate(@PathVariable("id") String id, Model model) {
			model.addAttribute("Promociones", pro_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Promociones no encontrado")));
			return "Promocion";
		}
		
		@PostMapping("/save")
		public String PromocionesSaveProcess(@ModelAttribute("contacto") Promociones promociones) {
			if(promociones.getId().isEmpty()) {
				promociones.setId(null);
			}
			pro_repositorio.save(promociones);
			return "redirect:/Promociones/lista";
		}
		
		@GetMapping("/delete/{id}")
		public String PromocionesDeleteProcess(@PathVariable("id") String id) {
			pro_repositorio.deleteById(id);
			return "redirect:/Promociones/lista";
			
		}

	}

