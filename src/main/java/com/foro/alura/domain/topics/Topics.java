package com.foro.alura.domain.topics;

import com.foro.alura.domain.courses.Courses;
import com.foro.alura.domain.responses.Responses;
import com.foro.alura.domain.users.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "topics")
@Entity(name = "Topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Users author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Courses course;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private Set<Responses> responses = new HashSet<>();

    public Topics(Long id) {
        this.id = id;
    }

    public Topics(DataRegisterTopic dataRegisterTopic) {
        this.title = dataRegisterTopic.title();
        this.message = dataRegisterTopic.message();
        this.status = dataRegisterTopic.status();
        this.author = dataRegisterTopic.author();
        this.course = dataRegisterTopic.course();

    }

    public void updateData(DataUpdateTopic dataUpdateTopic) {
        if (dataUpdateTopic.title() != null) {
            this.title = dataUpdateTopic.title();
        }
        if (dataUpdateTopic.message() != null) {
            this.message = dataUpdateTopic.message();
        }
        if (dataUpdateTopic.status() != null) {
            this.status = dataUpdateTopic.status();
        }
        if (dataUpdateTopic.author() != null) {
            this.author = new Users(dataUpdateTopic.author().getId());
        }
        if (dataUpdateTopic.course() != null) {
            this.course = new Courses(dataUpdateTopic.course().getId());
        }
    }


}
