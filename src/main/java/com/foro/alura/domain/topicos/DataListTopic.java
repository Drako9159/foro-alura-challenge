package com.foro.alura.domain.topicos;

import com.foro.alura.model.Topico;

public record DataListTopic(Long id, String title, String message, String createdAt) {
    public DataListTopic(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getfechaCreacion().toString());
    }
}
