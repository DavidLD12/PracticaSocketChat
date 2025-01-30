package com.davidlopez.practicaSockets.controller;

import com.davidlopez.practicaSockets.models.Mensaje;
import com.davidlopez.practicaSockets.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Endpoint para guardar un mensaje
    @PostMapping
    public Mensaje guardarMensaje(@RequestBody Mensaje mensaje) {
        // Verificar si se ha proporcionado una imagen
        if (mensaje.getImagenUrl() != null && !mensaje.getImagenUrl().isEmpty()) {
            // Agregar la ruta de la imagen al campo imagenUrl
            mensaje.setImagenUrl("http://localhost:8080/images/" + mensaje.getImagenUrl());
        }

        return mensajeService.guardarMensaje(mensaje); // Guardamos el mensaje en la base de datos
    }

    // Endpoint para obtener todos los mensajes
    @GetMapping
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeService.obtenerTodosLosMensajes();
    }

    // Endpoint para obtener mensajes de un usuario específico
    @GetMapping("/usuario/{username}")
    public List<Mensaje> obtenerMensajesPorUsuario(@PathVariable String username) {
        return mensajeService.obtenerMensajesPorUsuario(username);
    }
}
