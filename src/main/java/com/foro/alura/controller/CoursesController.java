package com.foro.alura.controller;

import com.foro.alura.domain.courses.*;
import com.foro.alura.infra.errors.HandleJson;
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
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(new DataResponseCourse(
                course.getId(),
                course.getName(),
                course.getType()
        ));
    }

    @GetMapping
    public ResponseEntity<Page<DataListCourse>> getCourses(Pageable pageable) {
        return ResponseEntity.ok(coursesRepository.findAll(pageable).map(DataListCourse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseCourse> getCourse(@PathVariable Long id) {
        if (!coursesRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Courses course = coursesRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataResponseCourse(
                course.getId(),
                course.getName(),
                course.getType()
        ));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseCourse> updateCourse(@RequestBody @Valid DataUpdateCourse dataUpdateCourse) {
        if (!coursesRepository.existsById(dataUpdateCourse.id()))
            return new ResponseEntity(new HandleJson().withMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
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
    public ResponseEntity<DataResponseCourse> deleteCourse(@PathVariable Long id) {
        if (!coursesRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("CURSO_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Courses course = coursesRepository.getReferenceById(id);
        coursesRepository.delete(course);
        return ResponseEntity.noContent().build();
    }
}
