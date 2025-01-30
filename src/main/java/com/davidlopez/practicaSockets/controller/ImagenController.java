package com.davidlopez.practicaSockets.controller;

import com.davidlopez.practicaSockets.models.Mensaje;
import com.davidlopez.practicaSockets.repositories.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ImagenController {

    private static final String UPLOAD_DIR = "C:/Users/David/Desktop/2DAM/PROCESOS/practicaSockets/src/main/resources/images/";


    @Autowired
    private MensajeRepository mensajeRepository;

    @GetMapping("/imagenes")
    public ResponseEntity<List<String>> obtenerImagenes() {
        // Lógica para obtener las URLs de las imágenes asociadas a los mensajes
        List<String> imagenes = mensajeRepository.findAll()
                .stream()
                .map(Mensaje::getImagenUrl) // Obtener las URLs de las imágenes
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return ResponseEntity.ok(imagenes);
    }
    @PostMapping("/imagenes")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); // Añadir un prefijo único
            String filePath = UPLOAD_DIR + fileName;

            // Guardar el archivo en la carpeta "images"
            Files.copy(file.getInputStream(), Paths.get(filePath));

            // Retornar la URL de la imagen guardada
            return ResponseEntity.ok("http://localhost:8080/images/" + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir la imagen");
        }
    }
}
