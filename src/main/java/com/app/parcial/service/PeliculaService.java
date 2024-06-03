package com.app.parcial.service;

import com.app.parcial.entity.Pelicula;
import com.app.parcial.repository.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PeliculaService {

    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    private PeliculaRepositorio peliculaRepository;

    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> findById(String id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula save(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void deleteById(String id) {
        peliculaRepository.deleteById(id);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            return fileName;  // Retornamos el nombre del archivo
        }
        return null;
    }
}
