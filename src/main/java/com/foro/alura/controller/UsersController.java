package com.foro.alura.controller;

import com.foro.alura.domain.users.*;
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
@RequestMapping("/usuarios")
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseUser> saveUser(@RequestBody @Valid DataRegisterUser dataRegisterUser, UriComponentsBuilder uriComponentsBuilder) {
        if (usersRepository.findByEmail(dataRegisterUser.email()) != null)
            return new ResponseEntity(new HandleJson().withMessage("USER_EXIST"), HttpStatus.BAD_REQUEST);
        Users user = usersRepository.save(new Users(dataRegisterUser));
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(new DataResponseUser(user));
    }

    @GetMapping
    public ResponseEntity<Page<DataListUser>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(usersRepository.findAll(pageable).map(DataListUser::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseUser> getUser(@PathVariable Long id) {
        if (!usersRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Users user = usersRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataResponseUser(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!usersRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Users user = usersRepository.getReferenceById(id);
        usersRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseUser> updateUser(@RequestBody @Valid DataUpdateUser dataUpdateUser) {
        if (!usersRepository.existsById(dataUpdateUser.id()))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Users user = usersRepository.getReferenceById(dataUpdateUser.id());
        user.updateData(dataUpdateUser);
        return ResponseEntity.ok(new DataResponseUser(user));
    }
}
