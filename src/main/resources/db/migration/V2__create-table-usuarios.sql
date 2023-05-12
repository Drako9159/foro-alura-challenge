CREATE TABLE IF NOT EXISTS usuarios
(
    id             BIGSERIAL    NOT NULL,
    nombre         VARCHAR(100) NOT NULL UNIQUE,
    correo         VARCHAR(300) NOT NULL UNIQUE,
    password       VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
