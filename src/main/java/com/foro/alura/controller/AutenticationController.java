package com.foro.alura.controller;

import com.foro.alura.infra.errors.HandleJson;
import com.foro.alura.infra.security.DataRecordJWTToken;
import com.foro.alura.infra.security.TokenService;
import com.foro.alura.users.User;
import com.foro.alura.users.UserRecordAutentication;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AutenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DataRecordJWTToken> autentiqueUser(@RequestBody @Valid UserRecordAutentication userRecordAutentication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userRecordAutentication.user(), userRecordAutentication.password());
        try {
            var userAutenticated = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.tokenGenerator((User) userAutenticated.getPrincipal());
            return ResponseEntity.ok(new DataRecordJWTToken(JWTtoken));
        } catch (AuthenticationException e) {
            return new ResponseEntity(new HandleJson().withMessage("INVALID_PASSWORD"), HttpStatus.FORBIDDEN);
        }
    }
}