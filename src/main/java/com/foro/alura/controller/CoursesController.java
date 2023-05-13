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
public class CoursesController {
    private final CoursesRepository coursesRepository;

    public CoursesController(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseCourse> saveCourse(@RequestBody @Valid DataRegisterCourse dataRegisterCourse, UriComponentsBuilder uriComponentsBuilder) {
        Courses course = coursesRepository.save(new Courses(dataRegisterCourse));
        DataResponseCourse dataResponseCourse = new DataResponseCourse(
                course.getId(),
                course.getName(),
                course.getType()
        );
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseCourse);
    }

    @GetMapping
    public ResponseEntity<Page<DataListCourse>> getCourses(Pageable pageable) {
        return ResponseEntity.ok(coursesRepository.findAll(pageable).map(DataListCourse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        if (!coursesRepository.existsById(id))
            return new ResponseEntity(new HandleErrors().errorWithMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Courses course = coursesRepository.getReferenceById(id);
        var dataListCourse = new DataResponseCourse(
                course.getId(),
                course.getName(),
                course.getType()
        );
        return ResponseEntity.ok(dataListCourse);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCourse(@RequestBody @Valid DataUpdateCourse dataUpdateCourse) {
        if (!coursesRepository.existsById(dataUpdateCourse.id()))
            return new ResponseEntity(new HandleErrors().errorWithMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Courses course = coursesRepository.getReferenceById(dataUpdateCourse.id());
        course.updateData(dataUpdateCourse);
        return ResponseEntity.ok(new DataResponseCourse(
                course.getId(),
                course.getName(),
                course.getType()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        if (!coursesRepository.existsById(id))
            return new ResponseEntity(new HandleErrors().errorWithMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Courses course = coursesRepository.getReferenceById(id);
        coursesRepository.delete(course);
        return ResponseEntity.noContent().build();
    }
}
