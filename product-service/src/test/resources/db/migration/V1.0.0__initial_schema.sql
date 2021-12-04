CREATE TABLE products (
    id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_description VARCHAR(255) NOT NULL,
    price NUMERIC NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_categories (
    product_id BIGINT NOT NULL,
    categories VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES products(id)
);