package com.foro.alura.domain.topics;

import com.foro.alura.domain.courses.Courses;
import com.foro.alura.domain.users.Users;
import com.foro.alura.model.StatusTopico;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public record DataResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        StatusTopico status,
        Users author,
        Courses course
) {


}


