package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Cursos;
import com.foro.alura.domain.usuarios.Usuarios;
import com.foro.alura.model.Curso;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        StatusTopico status,
        @NotNull
        Usuarios autor,
        @NotNull
        Cursos curso

) {
}
