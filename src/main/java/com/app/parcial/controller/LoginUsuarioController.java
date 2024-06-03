package com.app.parcial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.parcial.entity.Usuario;
import com.app.parcial.repository.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginUsuarioController {
	
	@Autowired
	private UsuarioRepositorio usRep;
	
	@PostMapping("/login/usuario")
	public String login(@RequestParam("correo") String correo, @RequestParam("contraseña") String contraseña,Model model,HttpSession session) {
		Optional<Usuario> optionalUsuario=usRep.findByCorreoAndContraseña(correo, contraseña);
		Usuario usuario = optionalUsuario.orElse(null);
		if(usuario !=null) {
			session.setAttribute("usuario", usuario);
			model.addAttribute("usuario", usuario);
			return "redirect:/index";
		} else {
			model.addAttribute("error", "El correo o la contraseña es incorrecto");
			return "loginUsuario";
		}
	}
}
