package com.foro.alura.controller;

import com.foro.alura.infra.errors.HandleJson;
import com.foro.alura.infra.security.DataRegisterHash;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cript")
public class HashController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> hashCrypt(@RequestBody @Valid DataRegisterHash dataRegisterHash) {

        if (dataRegisterHash.password() == null)
            return new ResponseEntity(new HandleJson().withMessage("NOT_PASSWORD_PROVIDER"), HttpStatus.NOT_FOUND);

        String pw_hash = BCrypt.hashpw(dataRegisterHash.password(), BCrypt.gensalt(10));
        Map<String, Object> hash = new HashMap<>();
        hash.put("hash", pw_hash);
        return ResponseEntity.ok(hash);
    }


}
