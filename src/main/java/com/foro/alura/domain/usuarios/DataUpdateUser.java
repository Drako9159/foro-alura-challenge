package com.foro.alura.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        String correo,
        String contrasena
) {
}
