CREATE TABLE IF NOT EXISTS courses
(
    id      BIGSERIAL    NOT NULL,
    name    VARCHAR(100) NOT NULL,
    type    VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
