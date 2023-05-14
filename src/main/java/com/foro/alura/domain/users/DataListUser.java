package com.foro.alura.domain.users;

public record DataListUser(Long id, String name, String email) {
    public DataListUser(Users user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
