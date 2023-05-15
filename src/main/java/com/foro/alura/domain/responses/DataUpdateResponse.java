package com.foro.alura.domain.responses;

import com.foro.alura.domain.topics.Topics;
import com.foro.alura.domain.users.Users;
import jakarta.validation.constraints.NotNull;

public record DataUpdateResponse(
        @NotNull
        Long id,
        String message,

        Boolean solution,
        Topics topic,
        Users author
) {
    public DataUpdateResponse(@NotNull Long id, String message, Boolean solution) {
        this(id, message, solution, null, null);
    }
}
