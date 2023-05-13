package com.foro.alura.domain.respuestas;

import com.foro.alura.domain.topicos.Topicos;
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

    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();

    @Column(name = "solucion")
    private Boolean solucion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topicos topico;



}
