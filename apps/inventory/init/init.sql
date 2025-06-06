CREATE TABLE IF NOT EXISTS inventory (
    product_id BIGINT PRIMARY KEY,
    quantity INT NOT NULL
);

INSERT INTO inventory (product_id, quantity) VALUES
(1, 25),
(2, 100),
(3, 40),
(4, 60),
(5, 30),
(6, 20),
(7, 55),
(8, 75),
(9, 15),
(10, 80);
