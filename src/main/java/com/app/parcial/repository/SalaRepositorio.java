package com.app.parcial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.parcial.entity.Sala;

public interface SalaRepositorio extends MongoRepository<Sala, String>{
	
	List<Sala> findAllById(List<String> salaIdsString);

}