package com.foro.alura.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull
        Long id,
        String name,
        String email,
        String password
) {
}
