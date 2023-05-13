package com.foro.alura.domain.topicos;

import com.foro.alura.domain.cursos.Cursos;
import com.foro.alura.domain.usuarios.Usuarios;
import com.foro.alura.model.Curso;
import com.foro.alura.model.StatusTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuarios autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Cursos curso;
/*
    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    private Set<Respuesta> respuesta = new HashSet<>();*/

    public Topicos(Long id) {
        this.id = id;
    }

    public Topicos(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.status = datosRegistroTopico.status();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();

        }
        if (datosActualizarTopico.status() != null) {
            this.status = datosActualizarTopico.status();
        }
        if (datosActualizarTopico.autor() != null) {
            this.autor = new Usuarios(datosActualizarTopico.autor().getId());
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = new Cursos(datosActualizarTopico.curso().getId());
        }
    }


}
