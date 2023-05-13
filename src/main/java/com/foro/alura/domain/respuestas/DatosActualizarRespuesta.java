package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topicos;
import com.foro.alura.domain.usuarios.Usuarios;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        String mensaje,
        Boolean solucion,
        Topicos topico,
        Usuarios autor
) {
    public DatosActualizarRespuesta(@NotNull Long id, String mensaje, Boolean solucion) {
        this(id, mensaje, solucion, null, null);
    }
}
