CREATE TABLE categories
(
    id         VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

ALTER TABLE news
    ADD categories_id VARCHAR(255);

ALTER TABLE news
    ADD CONSTRAINT FK_NEWS_ON_CATEGORIES FOREIGN KEY (categories_id) REFERENCES categories (id);

