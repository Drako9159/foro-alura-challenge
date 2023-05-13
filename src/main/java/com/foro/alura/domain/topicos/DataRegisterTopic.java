package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Courses;
import com.foro.alura.domain.usuarios.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        StatusTopico status,
        @NotNull
        Users author,
        @NotNull
        Courses course

) {
}
