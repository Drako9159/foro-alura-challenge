package com.foro.alura.domain.cursos;

public record DatosListCurso(Long id, String nombre, String tipo) {
    public DatosListCurso(Cursos curso) {
        this(curso.getId(), curso.getNombre(), curso.getTipo());
    }
}
