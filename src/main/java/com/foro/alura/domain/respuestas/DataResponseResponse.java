package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topics;
import com.foro.alura.domain.usuarios.Users;

import java.time.LocalDateTime;

public record DataResponseResponse(
        Long id,
        String message,
        LocalDateTime createdAt,
        Boolean solution,
        Topics topic,
        Users author
) {
}
