INSERT INTO
    orders (id, creationtimestamp, updatetimestamp, confirmed, shippedout)
VALUES
    (12345, TIMESTAMP '2021-01-10 12:20:40', TIMESTAMP '2021-01-25 07:37:14', 1, 1),
    (23456, TIMESTAMP '2021-12-09 22:57:40', TIMESTAMP '2021-12-10 09:18:50', 1, 0),
    (34567, TIMESTAMP '2021-12-10 13:57:40', TIMESTAMP '2021-12-10 13:57:40', 0, 0);

INSERT INTO
    order_items (id, product_id, price_per_unit, count, order_id)
VALUES
    (45678, 1, 10, 1, 12345),
    (56789, 2, 19.99, 10, 12345),
    (66789, 1, 12, 7, 23456),
    (77889, 4, 0.9, 100, 34567);