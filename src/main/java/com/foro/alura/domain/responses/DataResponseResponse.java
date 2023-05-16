package com.foro.alura.domain.responses;

import java.time.LocalDateTime;

public record DataResponseResponse(
        Long id,
        String message,
        LocalDateTime createdAt,
        Boolean solution,
        Long topic,
        Long author

) {
}
