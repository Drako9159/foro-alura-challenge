CREATE TABLE IF NOT EXISTS topics
(
    id                  BIGSERIAL    NOT NULL,
    title               VARCHAR(100) NOT NULL,
    message             VARCHAR(300) NOT NULL,
    created_at           TIMESTAMP    NOT NULL,
    status              VARCHAR(100) NOT NULL,
    author              BIGSERIAL    NOT NULL,
    course              BIGSERIAL    NOT NULL,
    PRIMARY KEY (id)
);
