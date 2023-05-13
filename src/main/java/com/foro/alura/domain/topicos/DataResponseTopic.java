package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Courses;
import com.foro.alura.domain.usuarios.Users;
import com.foro.alura.model.StatusTopico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        StatusTopico status,
        Users autor,
        Courses curso
) {
}
