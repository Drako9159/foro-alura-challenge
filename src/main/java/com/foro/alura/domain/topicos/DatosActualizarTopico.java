package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Cursos;
import com.foro.alura.domain.usuarios.Usuarios;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        StatusTopico status,
        Usuarios autor,
        Cursos curso
) {
        public DatosActualizarTopico(@NotNull Long id, String titulo, String mensaje, StatusTopico status){
                this(id, titulo, mensaje, status, null, null);
        }
}
