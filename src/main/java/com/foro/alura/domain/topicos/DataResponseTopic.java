package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Courses;
import com.foro.alura.domain.usuarios.Users;
import com.foro.alura.model.StatusTopico;

import java.time.LocalDateTime;

public record DataResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        StatusTopico status,
        Users author,
        Courses course
) {
}
