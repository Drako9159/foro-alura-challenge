package com.foro.alura.controller;

import com.foro.alura.domain.cursos.*;
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
@RequestMapping("/cursos")
public class CursosController {
    private final CursosRepository cursosRepository;

    public CursosController(CursosRepository cursosRepository) {
        this.cursosRepository = cursosRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> saveCourse(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder) {
        Cursos curso = cursosRepository.save(new Cursos(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getTipo()
        );
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListCurso>> getCourses(Pageable pageable) {
        return ResponseEntity.ok(cursosRepository.findAll(pageable).map(DatosListCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        if (!cursosRepository.existsById(id))
            return new ResponseEntity(new HandleErrors().errorWithMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Cursos curso = cursosRepository.getReferenceById(id);
        var datosCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getTipo()
        );
        return ResponseEntity.ok(datosCurso);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCourse(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        if (!cursosRepository.existsById(datosActualizarCurso.id()))
            return new ResponseEntity(new HandleErrors().errorWithMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Cursos curso = cursosRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getTipo()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        if (!cursosRepository.existsById(id))
            return new ResponseEntity(new HandleErrors().errorWithMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Cursos curso = cursosRepository.getReferenceById(id);
        cursosRepository.delete(curso);
        return ResponseEntity.noContent().build();
    }


}
