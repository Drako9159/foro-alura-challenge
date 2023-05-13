package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topics;
import com.foro.alura.domain.usuarios.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterResponse(
        @NotBlank
        String message,
        @NotNull
        Topics topic,
        @NotNull
        Users author
) {
}
