package com.foro.alura.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "Users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    public Users(Long id) {
        this.id = id;
    }

    public Users(DataRegisterUser dataRegisterUser) {
        this.name = dataRegisterUser.name();
        this.email = dataRegisterUser.email();
        this.password = dataRegisterUser.password();
    }

    public void updateData(DataUpdateUser dataUpdateUser) {
        if (dataUpdateUser.name() != null) {
            this.name = dataUpdateUser.name();
        }
        if (dataUpdateUser.email() != null) {
            this.email = dataUpdateUser.email();
        }
        if (dataUpdateUser.password() != null) {
            this.password = dataUpdateUser.password();
        }
    }


}
