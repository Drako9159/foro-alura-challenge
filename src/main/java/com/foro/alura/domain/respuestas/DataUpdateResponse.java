package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topics;
import com.foro.alura.domain.usuarios.Users;
import jakarta.validation.constraints.NotNull;

public record DataUpdateResponse(
        @NotNull
        Long id,
        String message,
        Boolean solution,
        Topics topic,
        Users author
) {
    public DataUpdateResponse(@NotNull Long id, String message, Boolean solution) {
        this(id, message, solution, null, null);
    }
}
