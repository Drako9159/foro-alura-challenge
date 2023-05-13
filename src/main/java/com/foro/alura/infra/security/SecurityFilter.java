package com.foro.alura.infra.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.foro.alura.users.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ObjectMapper mapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            try {
                var token = authHeader.replace("Bearer ", "");
                var validate = tokenService.getSubject(token); // extract validate

                if (validate != null) {
                    User user = new User();
                    var autentication = new UsernamePasswordAuthenticationToken(user, null,
                            user.getAuthorities()); // Fuerza inicio de sesion
                    SecurityContextHolder.getContext().setAuthentication(autentication);
                }
            } catch (Exception e){
                handleFilterError(response, "TOKEN_IS_OUT");
            }
        }
        filterChain.doFilter(request, response);
    }

    public void handleFilterError(HttpServletResponse response, String message) throws IOException {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", message);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), errorDetails);
    }
}
