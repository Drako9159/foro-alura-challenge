package com.foro.alura.domain.topics;


public record DataListTopic(Long id, String title, String message, String status, String createdAt) {
    public DataListTopic(Topics topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus().toString(), topic.getCreatedAt().toString());
    }
}
