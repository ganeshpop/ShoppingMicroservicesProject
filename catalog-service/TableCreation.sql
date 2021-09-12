CREATE DATABASE catalog;
use catalog;
DROP TABLE products;
CREATE TABLE products (id BIGINT AUTO_INCREMENT, code VARCHAR(10), name VARCHAR(20), description VARCHAR(30), price DOUBLE, PRIMARY KEY (id));


INSERT INTO products VALUES
(NULL,'P1001','Snow Shovel','Basic 22 inch',9.99),
(NULL,'P1002','Snow Shovel','Deluxe 24 inch',19.99),
(NULL,'P1003','Snow Shovel','Super Deluxe 26 inch',49.99),
(NULL,'P1004','Ice Scraper','Windshield 4 inch',3.99),
(NULL,'P1005','Roof Rake','Lightweight Metal Rake 65 Inch',27.99),
(NULL,'P1006','Roof Rake','Lightweight Metal Rake 35 Inch',17.99);


TRUNCATE TABLE products;

INSERT INTO products
VALUES (NULL, 'P1001', 'Snow Shovel', 'Basic 22 inch', 9.99),
       (NULL, 'P1002', 'Snow Shovel', 'Basic 15 inch', 9.99),
       (NULL, 'P1003', 'Snow Shovel', 'Deluxe 24 inch', 19.99),
       (NULL, 'P1004', 'Snow Shovel', 'Super Deluxe 26 inch', 49.99),
       (NULL, 'P1005', 'Ice Scraper', 'Windshield 4 inch', 3.99),
       (NULL, 'P1006', 'Roof Rake', 'Lightweight Metal Rake 65 Inch', 27.99),
       (NULL, 'P1007', 'Roof Rake', 'Lightweight Metal Rake 35 Inch', 17.99),
       (NULL, 'P1008', 'Roof Rake', 'Lightweight Metal Rake 25 Inch', 10.99);

