CREATE TABLE users
(
    id         VARCHAR(255)                NOT NULL,
    full_name  VARCHAR(200)                NOT NULL,
    email      VARCHAR(200)                NOT NULL,
    password   VARCHAR(200)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);