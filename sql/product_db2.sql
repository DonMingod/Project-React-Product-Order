CREATE DATABASE IF NOT EXISTS product_db2;
USE product_db2;

CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2)
);

INSERT INTO products (name, price)
VALUES ('Pizza Pepperoni', 35.00);
