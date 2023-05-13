CREATE TABLE IF NOT EXISTS cursos
(
    id           BIGSERIAL    NOT NULL,
    nombre       VARCHAR(100) NOT NULL,
    tipo         VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
