package com.foro.alura.domain.responses;

import com.foro.alura.domain.topics.Topics;
import com.foro.alura.domain.users.Users;

import java.time.LocalDateTime;

public record DataResponseResponse(
        Long id,
        String message,
        LocalDateTime createdAt,
        Boolean solution,
        Topics topic,
        Users author
) {
}
