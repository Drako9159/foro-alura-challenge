package com.foro.alura.domain.topics;

import com.foro.alura.model.StatusTopico;

import java.time.LocalDateTime;

public record DataResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        StatusTopico status,
        Long author,
        Long course
) {


}


