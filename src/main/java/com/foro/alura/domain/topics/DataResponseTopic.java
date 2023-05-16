package com.foro.alura.domain.topics;

import com.foro.alura.model.StatusTopico;

import java.time.LocalDateTime;
import java.util.List;

public record DataResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        StatusTopico status,
        Long author,
        Long course,
        List<Long> responses

) {


}


