package com.foro.alura.domain.topics;

import com.foro.alura.domain.courses.Courses;
import com.foro.alura.domain.users.Users;
import com.foro.alura.model.StatusTopico;

import java.time.LocalDateTime;

public record DataResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        StatusTopico status,
        Users author,
        Courses course
) {
}
