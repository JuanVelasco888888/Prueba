package com.app.parcial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.parcial.entity.Contacto;

public interface ContactoRepositorio extends MongoRepository<Contacto, String>{
	
	List<Contacto> findAllById(List<String> contactoIdsString);

}