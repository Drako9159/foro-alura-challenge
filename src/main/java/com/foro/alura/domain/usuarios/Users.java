package com.foro.alura.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo", unique = true)
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;

    public User(Long id) {
        this.id = id;
    }

    public User(DataRegisterUser datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correo = datosRegistroUsuario.correo();
        this.contrasena = datosRegistroUsuario.contrasena();
    }

    public void actualizarDatos(DataUpdateUser datosActualizarUsuario) {
        if (datosActualizarUsuario.nombre() != null) {
            this.nombre = datosActualizarUsuario.nombre();
        }
        if (datosActualizarUsuario.correo() != null) {
            this.correo = datosActualizarUsuario.correo();
        }
        if (datosActualizarUsuario.contrasena() != null) {
            this.contrasena = datosActualizarUsuario.contrasena();
        }
    }


}