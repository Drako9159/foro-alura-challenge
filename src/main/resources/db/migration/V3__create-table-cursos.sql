CREATE TABLE IF NOT EXISTS cursos
(
    id             BIGSERIAL    NOT NULL,
    nombre         VARCHAR(100) NOT NULL UNIQUE,
    tipo         VARCHAR(300) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
