package com.foro.alura.controller;

import com.foro.alura.domain.cursos.Cursos;
import com.foro.alura.domain.topicos.*;
import com.foro.alura.domain.usuarios.Usuarios;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    private final TopicosRepository topicosRepository;

    public TopicosController(TopicosRepository topicosRepository) {
        this.topicosRepository = topicosRepository;
    }

    @GetMapping
    public ResponseEntity<Page<DatosListTopico>> listarTopicos(Pageable pageable) {
        return ResponseEntity.ok(topicosRepository.findAll(pageable).map(DatosListTopico::new));
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> guardarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroTopico);
        Topicos topicos = topicosRepository.save(new Topicos(datosRegistroTopico));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topicos.getId(),
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getFechaDeCreacion(),
                topicos.getStatus(),
                new Usuarios(topicos.getAutor().getId()),
                new Cursos(topicos.getCurso().getId())
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornaDatosTopico(@PathVariable Long id) {
        if (!topicosRepository.existsById(id)) return new ResponseEntity("TOPIC_NOT_FOUND", HttpStatus.NOT_FOUND);
        Topicos topico = topicosRepository.getReferenceById(id);

        var datosTopico = new DatosListTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion().toString()
        );
        return ResponseEntity.ok(datosTopico);

    }

    @PutMapping()
    @Transactional
    public ResponseEntity ActualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        if (!topicosRepository.existsById(datosActualizarTopico.id()))
            return new ResponseEntity("TOPIC_NOT_FOUND", HttpStatus.NOT_FOUND);
        Topicos topico = topicosRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                new Usuarios(topico.getAutor().getId()),
                new Cursos(topico.getCurso().getId()))
        );
    }


    //LOGICAL DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        if (!topicosRepository.existsById(id)) return new ResponseEntity("TOPIC_NOT_FOUND", HttpStatus.NOT_FOUND);
        Topicos topico = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topico);
        return ResponseEntity.noContent().build();

    }


}
