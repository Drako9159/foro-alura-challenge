CREATE TABLE IF NOT EXISTS topicos
(
    id             BIGSERIAL    NOT NULL,
    titulo         VARCHAR(100) NOT NULL UNIQUE,
    mensaje        VARCHAR(300) NOT NULL,
    fecha_creacion  TIMESTAMP    NOT NULL,
    estado         VARCHAR(50)  NOT NULL,
    autor          BIGSERIAL    NOT NULL,
    curso          BIGSERIAL    NOT NULL,
    PRIMARY KEY (id)
);
