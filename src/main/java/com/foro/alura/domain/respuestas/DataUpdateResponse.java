package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topicos;
import com.foro.alura.domain.usuarios.Users;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        String mensaje,
        Boolean solucion,
        Topicos topico,
        Users autor
) {
    public DatosActualizarRespuesta(@NotNull Long id, String mensaje, Boolean solucion) {
        this(id, mensaje, solucion, null, null);
    }
}
