package com.app.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.parcial.entity.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class PerfilUsuarioWebController {
	@GetMapping("/usuario/perfil")
	public String mostrarPerfilUsuario(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		return "perfilUsuario";
	}
	

}
