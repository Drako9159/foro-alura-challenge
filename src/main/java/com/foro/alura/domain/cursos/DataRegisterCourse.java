package com.foro.alura.domain.cursos;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCourse(
        @NotBlank
        String name,
        @NotBlank
        String type
) {
}
