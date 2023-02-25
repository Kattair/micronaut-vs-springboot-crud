DROP TABLE IF EXISTS t_book;

CREATE TABLE t_book (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
)