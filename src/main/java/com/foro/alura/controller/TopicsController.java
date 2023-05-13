package com.foro.alura.controller;

import com.foro.alura.domain.courses.Courses;
import com.foro.alura.domain.topics.*;
import com.foro.alura.domain.users.Users;
import com.foro.alura.infra.errors.HandleErrors;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicsController {
    private final TopicsRepository topicsRepository;

    public TopicsController(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> getTopics(Pageable pageable) {
        return ResponseEntity.ok(topicsRepository.findAll(pageable).map(DataListTopic::new));
    }

    @PostMapping
    public ResponseEntity<DataResponseTopic> saveTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic, UriComponentsBuilder uriComponentsBuilder) {
        Topics topics = topicsRepository.save(new Topics(dataRegisterTopic));
        DataResponseTopic dataResponseTopic = new DataResponseTopic(
                topics.getId(),
                topics.getTitle(),
                topics.getMessage(),
                topics.getCreatedAt(),
                topics.getStatus(),
                new Users(topics.getAuthor().getId()),
                new Courses(topics.getCourse().getId())
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topics.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseTopic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopic(@PathVariable Long id) {
        if (!topicsRepository.existsById(id))
            return new ResponseEntity(new HandleErrors().errorWithMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Topics topic = topicsRepository.getReferenceById(id);

        var dataListTopic = new DataListTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt().toString()
        );
        return ResponseEntity.ok(dataListTopic);

    }

    @PutMapping()
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
        if (!topicsRepository.existsById(dataUpdateTopic.id()))
            return new ResponseEntity(new HandleErrors().errorWithMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Topics topic = topicsRepository.getReferenceById(dataUpdateTopic.id());
        topic.updateData(dataUpdateTopic);
        return ResponseEntity.ok(new DataResponseTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt(),
                topic.getStatus(),
                new Users(topic.getAuthor().getId()),
                new Courses(topic.getCourse().getId()))
        );
    }


    //LOGICAL DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        if (!topicsRepository.existsById(id))
            return new ResponseEntity(new HandleErrors().errorWithMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Topics topic = topicsRepository.getReferenceById(id);
        topicsRepository.delete(topic);
        return ResponseEntity.noContent().build();

    }


}
