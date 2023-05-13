package com.foro.alura.controller;

import com.foro.alura.domain.respuestas.*;
import com.foro.alura.domain.topicos.Topics;
import com.foro.alura.domain.usuarios.Users;
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
@RequestMapping("/respuestas")
public class ResponsesController {

    private final ResponsesRepository responsesRepository;

    public ResponsesController(ResponsesRepository responsesRepository) {
        this.responsesRepository = responsesRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseResponse> saveResponse(@RequestBody @Valid DataRegisterResponse dataRegisterResponse,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Responses response = responsesRepository.save(new Responses(dataRegisterResponse));
        DataResponseResponse dataResponseResponse = new DataResponseResponse(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt(),
                response.getSolution(),
                new Topics(response.getTopic().getId()),
                new Users(response.getAuthor().getId())
        );
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseResponse);
    }

    @GetMapping
    public ResponseEntity<Page<DataListResponse>> getResponses(Pageable pageable) {
        return ResponseEntity.ok(responsesRepository.findAll(pageable).map(DataListResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponse(@PathVariable Long id) {
        if (!responsesRepository.existsById(id)) return new ResponseEntity(new HandleErrors().errorWithMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Responses response = responsesRepository.getReferenceById(id);
        var dataListResponse = new DataListResponse(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt().toString(),
                response.getSolution()
        );
        return ResponseEntity.ok(dataListResponse);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateResponse(@RequestBody @Valid DataUpdateResponse dataUpdateResponse) {
        if (!responsesRepository.existsById(dataUpdateResponse.id()))
            return new ResponseEntity(new HandleErrors().errorWithMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Responses response = responsesRepository.getReferenceById(dataUpdateResponse.id());
        response.updateData(dataUpdateResponse);
        return ResponseEntity.ok(new DataResponseResponse(
                response.getId(),
                response.getMessage(),
                response.getCreatedAt(),
                response.getSolution(),
                new Topics(response.getTopic().getId()),
                new Users(response.getAuthor().getId()))
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        if (!responsesRepository.existsById(id)) return new ResponseEntity(new HandleErrors().errorWithMessage("RESPUESTA_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Responses response = responsesRepository.getReferenceById(id);
        responsesRepository.delete(response);
        return ResponseEntity.noContent().build();

    }
}
