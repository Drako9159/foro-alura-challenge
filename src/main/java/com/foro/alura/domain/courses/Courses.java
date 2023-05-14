package com.foro.alura.domain.courses;

import com.foro.alura.domain.topics.DataRegisterTopic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "courses")
@Entity(name = "Courses")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public Courses(Long id) {
        this.id = id;
    }

    public Courses(DataRegisterCourse dataRegisterCourse) {
        this.name = dataRegisterCourse.name();
        this.type = dataRegisterCourse.type();
    }

    public void updateData(DataUpdateCourse dataUpdateCourse) {
        if (dataUpdateCourse.name() != null) {
            this.name = dataUpdateCourse.name();
        }
        if (dataUpdateCourse.type() != null) {
            this.type = dataUpdateCourse.type();
        }
    }
}
