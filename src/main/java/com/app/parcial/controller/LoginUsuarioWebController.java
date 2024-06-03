package com.app.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginUsuarioWebController {
	@GetMapping({"usuario/login"})
	public String showLoginForm() {
		return "loginUsuario";
	}

}
