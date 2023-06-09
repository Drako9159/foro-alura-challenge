package com.foro.alura.domain.responses;

import com.foro.alura.domain.topics.Topics;
import com.foro.alura.domain.users.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterResponse(
        @NotBlank
        String message,
        @NotNull
        Boolean solution,
        @NotNull
        Topics topic,
        @NotNull
        Users author
) {
}
