CREATE TABLE IF NOT EXISTS respuestas
(
    id                 BIGSERIAL    NOT NULL,
    mensaje            VARCHAR(100) NOT NULL,
    fecha_de_creacion  TIMESTAMP    NOT NULL,
    solucion           BOOLEAN      NOT NULL,
    topico             BIGSERIAL    NOT NULL,
    autor              BIGSERIAL    NOT NULL
    PRIMARY KEY (id)
);
