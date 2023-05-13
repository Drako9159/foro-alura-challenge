package com.foro.alura.domain.responses;

public record DataListResponse(
        Long id,
        String message,
        String createdAt,
        Boolean solution
) {
    public DataListResponse(Responses responses) {
        this(responses.getId(), responses.getMessage(), responses.getCreatedAt().toString(), responses.getSolution());
    }

}
