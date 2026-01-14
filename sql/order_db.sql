CREATE DATABASE IF NOT EXISTS order_db;
USE order_db;

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    total DECIMAL(10,2),
    created_at DATE
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(100),
    price DECIMAL(10,2),
    quantity INT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
);

INSERT INTO orders (customer_name, total, created_at)
SELECT 'Juan Perez', 50.00, CURRENT_DATE
WHERE NOT EXISTS (
    SELECT 1 FROM orders WHERE id = 1
);

INSERT INTO order_items (product_id, product_name, price, quantity, order_id)
SELECT 101, 'Pizza Margarita', 25.00, 2, 1
WHERE EXISTS (
    SELECT 1 FROM orders WHERE id = 1
)
AND NOT EXISTS (
    SELECT 1 FROM order_items WHERE id = 1
);
