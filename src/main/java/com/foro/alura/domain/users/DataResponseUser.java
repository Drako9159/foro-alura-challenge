package com.foro.alura.domain.users;

public record DataResponseUser(
        Long id,
        String name,
        String email
) {
    public DataResponseUser(Users user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
