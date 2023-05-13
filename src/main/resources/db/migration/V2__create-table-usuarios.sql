CREATE TABLE IF NOT EXISTS usuarios
(
    id             BIGSERIAL    NOT NULL,
    nombre         VARCHAR(100) NOT NULL,
    correo         VARCHAR(100) NOT NULL UNIQUE,
    contrasena     VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
