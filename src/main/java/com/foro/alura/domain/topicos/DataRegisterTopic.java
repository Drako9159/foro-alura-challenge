package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Courses;
import com.foro.alura.domain.usuarios.Users;
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
        Users autor,
        @NotNull
        Courses curso

) {
}