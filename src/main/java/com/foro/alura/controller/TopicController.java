package com.foro.alura.controller;

import com.foro.alura.domain.topicos.DatosListTopico;
import com.foro.alura.domain.topicos.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {
    private final TopicoRepository topicoRepository;

    public TopicController(TopicoRepository topicoRepository){
        this.topicoRepository = topicoRepository;
    }

    @GetMapping
    public ResponseEntity <Page<DatosListTopico>> listarTopicos(Pageable pageable){
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosListTopico::new));
    }






}
