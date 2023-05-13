CREATE TABLE IF NOT EXISTS responses
(
    id              BIGSERIAL    NOT NULL,
    message         VARCHAR(300) NOT NULL,
    createdAt       TIMESTAMP    NOT NULL,
    solution        BOOLEAN      NOT NULL,
    topic           BIGSERIAL    NOT NULL,
    author          BIGSERIAL    NOT NULL,
    PRIMARY KEY (id)
);
