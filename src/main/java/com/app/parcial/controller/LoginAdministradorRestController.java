package com.app.parcial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.parcial.entity.Administrador;
import com.app.parcial.repository.AdministradoresRepositorio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginAdministradorRestController {
	
	@Autowired
	private AdministradoresRepositorio adRep;
	
	@PostMapping("/login/administrador")
	public String login(@RequestParam("correo") String correo, @RequestParam("password") String password, Model model, HttpSession session) {
		Optional<Administrador> optionalAdministrador = adRep.findByCorreoAndPassword(correo, password);
		Administrador administrador = optionalAdministrador.orElse(null);
		
		if(administrador != null) {
			session.setAttribute("administrador", administrador);
			model.addAttribute("administrador", administrador);
			return "redirect:/index2";
		} else {
			model.addAttribute("error", "El Correo o la Contraseña es incorrecto");
			return "loginAdministrador";
		}
	}
}
