CREATE TABLE IF NOT EXISTS topicos
(
    id                  BIGSERIAL    NOT NULL,
    titulo              VARCHAR(100) NOT NULL,
    mensaje             VARCHAR(300) NOT NULL,
    fecha_de_creacion   TIMESTAMP    NOT NULL,
    estado              VARCHAR(100) NOT NULL,
    autor               BIGSERIAL    NOT NULL,
    curso               BIGSERIAL    NOT NULL,
    PRIMARY KEY (id)
);
