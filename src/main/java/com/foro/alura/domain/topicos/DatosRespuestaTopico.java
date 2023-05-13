package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Cursos;
import com.foro.alura.domain.usuarios.Usuarios;
import com.foro.alura.model.StatusTopico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        StatusTopico status,
        Usuarios autor,
        Cursos curso
) {
}
