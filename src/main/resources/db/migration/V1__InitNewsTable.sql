CREATE TABLE news
(
    id          VARCHAR(255) NOT NULL,
    title       VARCHAR(200) NOT NULL,
    author      VARCHAR(200) NOT NULL,
    image_url   VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    content     VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    CONSTRAINT pk_news PRIMARY KEY (id)
);