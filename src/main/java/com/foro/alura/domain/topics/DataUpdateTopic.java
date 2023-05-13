package com.foro.alura.domain.topics;

import com.foro.alura.domain.courses.Courses;
import com.foro.alura.domain.users.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String title,
        String message,
        StatusTopico status,
        Users author,
        Courses course
) {
    public DataUpdateTopic(@NotNull Long id, String title, String message, StatusTopico status) {
        this(id, title, message, status, null, null);
    }
}
