package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Courses;
import com.foro.alura.domain.usuarios.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String title,
        String message,
        StatusTopico status,
        Users author,
        Courses course
) {
    public DataUpdateTopic(@NotNull Long id, String title, String message, StatusTopico status) {
        this(id, title, message, status, null, null);
    }
}
