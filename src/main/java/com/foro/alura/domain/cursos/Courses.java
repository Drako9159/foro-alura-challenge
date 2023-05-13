package com.foro.alura.domain.cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;

    public Cursos(Long id){
        this.id = id;
    }

    public Cursos(DatosRegistroCurso datosRegistroCurso){
        this.nombre = datosRegistroCurso.nombre();
        this.tipo = datosRegistroCurso.tipo();
    }


    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso){
        if(datosActualizarCurso.nombre() != null){
            this.nombre = datosActualizarCurso.nombre();
        }
        if(datosActualizarCurso.tipo() != null){
            this.tipo = datosActualizarCurso.tipo();
        }
    }
}
