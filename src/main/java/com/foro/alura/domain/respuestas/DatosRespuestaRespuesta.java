package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topicos;
import com.foro.alura.domain.usuarios.Usuarios;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Boolean solucion,
        Topicos topicos,
        Usuarios autor
) {
}
