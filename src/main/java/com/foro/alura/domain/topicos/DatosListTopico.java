package com.foro.alura.domain.topicos;


public record DatosListTopico(Long id, String title, String message, String createdAt) {
    public DatosListTopico(Topicos topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion().toString());
    }
}
