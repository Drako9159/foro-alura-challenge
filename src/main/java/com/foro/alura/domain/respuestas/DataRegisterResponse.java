package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topicos;
import com.foro.alura.domain.usuarios.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Topicos topico,
        @NotNull
        Users autor
) {
}
