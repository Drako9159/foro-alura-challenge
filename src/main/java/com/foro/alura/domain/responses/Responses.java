package com.foro.alura.domain.responses;

import com.foro.alura.domain.topics.Topics;
import com.foro.alura.domain.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "responses")
@Entity(name = "Responses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Responses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic")
    private Topics topic;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Users author;

    @Column(name = "solution")
    private Boolean solution = false;

    public Responses(DataRegisterResponse dataRegisterResponse){
        this.message = dataRegisterResponse.message();
        this.topic = new Topics(dataRegisterResponse.topic().getId());
        this.author = new Users(dataRegisterResponse.author().getId());
    }
    public void updateData(DataUpdateResponse dataUpdateResponse) {
        if (dataUpdateResponse.message() != null) {
            this.message = dataUpdateResponse.message();
        }
        if (dataUpdateResponse.solution() != null) {
            this.solution = dataUpdateResponse.solution();
        }
        if (dataUpdateResponse.topic() != null) {
            this.topic = new Topics(dataUpdateResponse.topic().getId());
        }
        if (dataUpdateResponse.author() != null) {
            this.author = new Users(dataUpdateResponse.author().getId());
        }
    }






}
