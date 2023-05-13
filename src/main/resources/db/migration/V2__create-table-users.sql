CREATE TABLE IF NOT EXISTS users
(
    id          BIGSERIAL    NOT NULL,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
