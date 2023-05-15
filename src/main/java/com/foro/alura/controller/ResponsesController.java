package com.foro.alura.controller;

import com.foro.alura.domain.responses.*;
import com.foro.alura.domain.topics.TopicsRepository;
import com.foro.alura.domain.users.UsersRepository;
import com.foro.alura.infra.errors.HandleJson;
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
@RequestMapping("/respuestas")
public class ResponsesController {

    private final ResponsesRepository responsesRepository;
    private final UsersRepository usersRepository;
    private final TopicsRepository topicsRepository;

    public ResponsesController(ResponsesRepository responsesRepository, UsersRepository usersRepository, TopicsRepository topicsRepository) {
        this.responsesRepository = responsesRepository;
        this.usersRepository = usersRepository;
        this.topicsRepository = topicsRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseResponse> saveResponse(@RequestBody @Valid DataRegisterResponse dataRegisterResponse,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        if (!usersRepository.existsById(dataRegisterResponse.author().getId()))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);

        if (!topicsRepository.existsById(dataRegisterResponse.topic().getId()))
            return new ResponseEntity(new HandleJson().withMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);



        Responses response = responsesRepository.save(new Responses(dataRegisterResponse));
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(new DataResponseResponse(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt(),
                response.getSolution(),
                response.getTopic().getId(),
                response.getAuthor().getId()
        ));


    }

    @GetMapping
    public ResponseEntity<Page<DataListResponse>> getResponses(Pageable pageable) {
        return ResponseEntity.ok(responsesRepository.findAll(pageable).map(DataListResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseResponse> getResponse(@PathVariable Long id) {
        if (!responsesRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Responses response = responsesRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataResponseResponse(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt(),
                response.getSolution(),
                response.getTopic().getId(),
                response.getAuthor().getId()
        ));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseResponse> updateResponse(@RequestBody @Valid DataUpdateResponse dataUpdateResponse) {
        if (!responsesRepository.existsById(dataUpdateResponse.id()))
            return new ResponseEntity(new HandleJson().withMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);

        if (!usersRepository.existsById(dataUpdateResponse.author().getId()))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);

        if (!topicsRepository.existsById(dataUpdateResponse.topic().getId()))
            return new ResponseEntity(new HandleJson().withMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);

        Responses response = responsesRepository.getReferenceById(dataUpdateResponse.id());
        response.updateData(dataUpdateResponse);
        return ResponseEntity.ok(new DataResponseResponse(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt(),
                response.getSolution(),
                response.getTopic().getId(),
                response.getAuthor().getId()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseResponse> deleteResponse(@PathVariable Long id) {
        if (!responsesRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Responses response = responsesRepository.getReferenceById(id);
        responsesRepository.delete(response);
        return ResponseEntity.noContent().build();

    }
}
