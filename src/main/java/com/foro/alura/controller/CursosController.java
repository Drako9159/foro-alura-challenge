package com.foro.alura.controller;

import com.foro.alura.domain.cursos.*;
import com.foro.alura.domain.usuarios.DatosListUsuario;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DatosListCurso>> listadoCurso(Pageable pageable) {
        return ResponseEntity.ok(cursosRepository.findAll(pageable).map(DatosListCurso::new));
    }

}
