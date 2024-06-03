package com.app.parcial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.parcial.entity.Promociones;


public interface PromocionesRepositorio extends MongoRepository<Promociones, String>{
	
	List<Promociones> findAllById(List<String> promocionesIdsString);

}