CREATE TABLE customers (
    id BIGINT NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    PRIMARY KEY (id)
);