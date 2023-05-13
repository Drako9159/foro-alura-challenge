package com.foro.alura.domain.cursos;

import jakarta.validation.constraints.NotNull;

public record DataUpdateCourse(
        @NotNull
        Long id,
        String name,
        String type
) {
}
