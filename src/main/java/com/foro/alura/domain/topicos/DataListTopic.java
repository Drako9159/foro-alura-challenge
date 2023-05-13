package com.foro.alura.domain.topicos;


public record DataListTopic(Long id, String title, String message, String createdAt) {
    public DataListTopic(Topics topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt().toString());
    }
}
