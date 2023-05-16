package com.foro.alura.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foro.alura.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.secret}")
    private String apiSecret;

    public String tokenGenerator(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("alura.forum")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateDateExpire())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }


    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            verifier = JWT.require(algorithm)
                    .withIssuer("alura.forum")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
        }
        if (verifier.getSubject() == null) {

            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generateDateExpire() {
        // 5 to 24

        return LocalDateTime.now().plusHours(20).toInstant(ZoneOffset.of("-05:00"));
    }
}
