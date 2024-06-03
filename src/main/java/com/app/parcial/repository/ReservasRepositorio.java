package com.app.parcial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.parcial.entity.Reserva;


public interface ReservasRepositorio extends MongoRepository<Reserva, String>{
	
	List<Reserva> findAllById(List<String> reservasIdsString);

}