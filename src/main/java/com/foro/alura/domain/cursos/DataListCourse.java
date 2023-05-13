package com.foro.alura.domain.cursos;

public record DataListCourse(Long id, String name, String type) {
    public DataListCourse(Courses course) {
        this(course.getId(), course.getName(), course.getType());
    }
}
