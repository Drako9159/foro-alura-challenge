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
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correo;

    private String contrasena;

    public Usuarios(Long id) {
        this.id = id;
    }

    public Usuarios(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correo = datosRegistroUsuario.correo();
        this.contrasena = datosRegistroUsuario.contrasena();
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario) {
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
