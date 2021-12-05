CREATE TABLE orders (
    id BIGINT NOT NULL,
    creationtimestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updatetimestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    confirmed BOOLEAN NOT NULL,
    shippedout BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_items (
    id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price_per_unit NUMERIC NOT NULL,
    count INTEGER NOT NULL,
    order_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);