package com.app.parcial.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.parcial.entity.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String>{
	Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
}
