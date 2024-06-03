package com.app.parcial.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.app.parcial.entity.Pelicula;

public interface PeliculaRepositorio extends MongoRepository<Pelicula, String> {
    List<Pelicula> findByGeneroContainingIgnoreCase(String genero);
}
