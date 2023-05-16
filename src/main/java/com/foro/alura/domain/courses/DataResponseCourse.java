package com.foro.alura.domain.courses;

public record DataResponseCourse(
        Long id,
        String name,
        String type
) {
    public DataResponseCourse(Courses course){
        this(course.getId(), course.getName(), course.getType());
    }
}
