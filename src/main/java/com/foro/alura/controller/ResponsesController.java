package com.foro.alura.controller;

import com.foro.alura.domain.respuestas.*;
import com.foro.alura.domain.topicos.Topicos;
import com.foro.alura.domain.usuarios.Usuarios;
import com.foro.alura.infra.errors.HandleErrors;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestasController {

    private final RespuestaRepository respuestaRepository;

    public RespuestasController(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> saveResponse(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                                                      UriComponentsBuilder uriComponentsBuilder) {
        Respuestas respuesta = respuestaRepository.save(new Respuestas(datosRegistroRespuesta));
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion(),
                respuesta.getSolucion(),
                new Topicos(respuesta.getTopico().getId()),
                new Usuarios(respuesta.getAutor().getId())
        );
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListRespuesta>> getResponses(Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponse(@PathVariable Long id) {
        if (!respuestaRepository.existsById(id)) return new ResponseEntity(new HandleErrors().errorWithMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Respuestas respuesta = respuestaRepository.getReferenceById(id);
        var datosRespuesta = new DatosListRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion().toString(),
                respuesta.getSolucion()
        );
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateResponse(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        if (!respuestaRepository.existsById(datosActualizarRespuesta.id()))
            return new ResponseEntity(new HandleErrors().errorWithMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Respuestas respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaDeCreacion(),
                respuesta.getSolucion(),
                new Topicos(respuesta.getTopico().getId()),
                new Usuarios(respuesta.getAutor().getId()))
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        if (!respuestaRepository.existsById(id)) return new ResponseEntity(new HandleErrors().errorWithMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Respuestas respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();

    }
}
