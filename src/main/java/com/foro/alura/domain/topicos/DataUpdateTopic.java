package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Courses;
import com.foro.alura.domain.usuarios.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        StatusTopico status,
        Users autor,
        Courses curso
) {
        public DatosActualizarTopico(@NotNull Long id, String titulo, String mensaje, StatusTopico status){
                this(id, titulo, mensaje, status, null, null);
        }
}
