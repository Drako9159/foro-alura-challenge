package com.foro.alura.controller;

import com.foro.alura.domain.cursos.Cursos;
import com.foro.alura.domain.cursos.CursosRepository;
import com.foro.alura.domain.cursos.DatosRegistroCurso;
import com.foro.alura.domain.cursos.DatosRespuestaCurso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    private final CursosRepository cursosRepository;

    public CursosController(CursosRepository cursosRepository){
        this.cursosRepository = cursosRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarUsuario(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder) {
        Cursos curso = cursosRepository.save(new Cursos(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getTipo()
        );
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }
}
