package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topicos;
import com.foro.alura.domain.usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Respuestas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mensaje")
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topicos topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuarios autor;

    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();

    @Column(name = "solucion")
    private Boolean solucion = false;

    public Respuestas(DatosRegistroRespuesta datosRegistroRespuesta){
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topico = new Topicos(datosRegistroRespuesta.topico().getId());
        this.autor = new Usuarios(datosRegistroRespuesta.autor().getId());
    }
    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.mensaje() != null) {
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
        if (datosActualizarRespuesta.solucion() != null) {
            this.solucion = datosActualizarRespuesta.solucion();
        }
        if (datosActualizarRespuesta.topico() != null) {
            this.topico = new Topicos(datosActualizarRespuesta.topico().getId());
        }
        if (datosActualizarRespuesta.autor() != null) {
            this.autor = new Usuarios(datosActualizarRespuesta.autor().getId());
        }
    }






}
