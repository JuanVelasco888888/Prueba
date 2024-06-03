package com.app.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.parcial.entity.Contacto;
import com.app.parcial.exception.NotFoundException;
import com.app.parcial.repository.ContactoRepositorio;

@Controller
	@RequestMapping(value = "contacto")
	public class ContactoWebController {
		
		@Autowired
		private ContactoRepositorio cot_repositorio;
		
		@GetMapping("/lista")
		public String contactoListTemplate(Model model) {
			model.addAttribute("contacto", cot_repositorio.findAll());
			return "lista-contacto";
		}
		
		@GetMapping("/crear")
		public String contactoNewTemplate(Model model) {
			model.addAttribute("contacto", new Contacto());
			return "formContacto";
		}
		
		@GetMapping("/edit/{id}")
		public String contactoEditTemplate(@PathVariable("id") String id, Model model) {
			model.addAttribute("contacto", cot_repositorio.findById(id).orElseThrow(() -> new NotFoundException("Contacto no encontrado")));
			return "formContacto";
		}
		
		@PostMapping("/save")
		public String ContactoSaveProcess(@ModelAttribute("contacto") Contacto contacto) {
			if(contacto.getId().isEmpty()) {
				contacto.setId(null);
			}
			cot_repositorio.save(contacto);
			return "redirect:/contacto/lista";
		}
		
		@GetMapping("/delete/{id}")
		public String contactoDeleteProcess(@PathVariable("id") String id) {
			cot_repositorio.deleteById(id);
			return "redirect:/contacto/lista";
			
		}

	}

