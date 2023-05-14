package com.foro.alura.domain.topics;

import com.foro.alura.domain.courses.Courses;
import com.foro.alura.domain.users.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        StatusTopico status,
        @NotNull
        Users author,
        @NotNull
        Courses course

) {
}
