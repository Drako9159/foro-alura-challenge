package com.foro.alura.domain.usuarios;

public record DatosListUsuario(Long id, String nombre, String email) {
    public DatosListUsuario(Usuarios usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
    }
}
