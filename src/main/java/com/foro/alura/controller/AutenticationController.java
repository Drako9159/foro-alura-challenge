package com.foro.alura.controller;

import com.foro.alura.infra.security.TokenService;
import com.foro.alura.infra.security.DataRecordJWTToken;
import com.foro.alura.users.User;
import com.foro.alura.users.UserRecordAutentication;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autentiqueUser(@RequestBody @Valid UserRecordAutentication userRecordAutentication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userRecordAutentication.user(), userRecordAutentication.password());



        var userAutenticated = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.tokenGenerator((User) userAutenticated.getPrincipal());


        return ResponseEntity.ok(new DataRecordJWTToken(JWTtoken));
    }
}