package com.app.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IniciarSesionController {
	@GetMapping({"/iniciarSesion"})
	public String showInicioForm() {
		return "iniciarSesion";
	}
}
