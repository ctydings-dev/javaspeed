USE scuba;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM products;
DELETE FROM customers;
DELETE FROM equipment;


INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('CMT-CUST','CHRISTOPHER','TYDINGS','24601');
UPDATE customers SET street = '10030 GEBHART DR';
UPDATE customers SET city = 'ANCHORAGE';
UPDATE customers SET state = 'AK';
UPDATE customers SET country = 'US';
UPDATE customers SET email = 'CTYDINGS@vt.edu';
UPDATE customers SET phone = '571-223-9334';
UPDATE customers SET post_code = '99515';
UPDATE customers SET date_of_birth = '1990-06-10';


INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('SHOP','SHOP','SHOP',-1);
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('1-CUST','CUSTOMER','1','1');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('2-CUST','CUSTOMER','2','2');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('3-CUST','CUSTOMERR','3','3');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('4-CUST','CUSTOMER','4','4');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('5-CUST','CUSTOMER','5','5');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('6-CUST','CUSTOMER','6','6');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('7-CUST','CUSTOMER','7','7');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('8-CUST','CUSTOMER','8','8');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('9-CUST','CUSTOMER','9','9');
INSERT INTO customers( customer_code, first_name, last_name,eve_id) VALUES ('10-CUST','CUSTOMER','10','10');
INSERT INTO customers( customer_code, first_name, last_name,eve_id,email) VALUES ('staff@divealaska.net','RON','FANCHER','10','staff@divealaska.net');
INSERT INTO products(product_name, sku, cost,price ,stock, barcode, is_active,eve_id, category_id) SELECT 'REGULATOR_1', 'REG_1',10,20,5,'BRC-REG-1',TRUE,1, category_id FROM categories WHERE category_name = 'REGULATORS';
INSERT INTO products(product_name, sku, cost,price ,stock, barcode, is_active,eve_id, category_id) SELECT 'REGULATOR_RENTAL', 'REG_RENT',10,0,1,'BRC-REG-RENT-1',TRUE,1, category_id FROM categories WHERE category_name = 'REGULATORS';

INSERT INTO products(product_name, sku, cost,price ,stock, barcode, is_active,eve_id, category_id) SELECT 'REGULATOR_2', 'REG_2',30,40,6,'BRC-REG-2',TRUE,2, category_id FROM categories WHERE category_name = 'REGULATORS';
INSERT INTO products(product_name, sku, cost,price , stock, barcode, is_active,eve_id, category_id) SELECT 'REGULATOR_3', 'REG_3',60,80,70,'BRC-REG-3',TRUE,30, category_id FROM categories WHERE category_name = 'REGULATORS';



SET @sku = 'REG_1';
SET @brand = 'MARES';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;

SET @sku = 'REG_RENT';
SET @brand = 'MARES';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;




SET @sku = 'REG_2';
SET @brand = 'SCUBAPRO';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;

SET @sku = 'REG_3';
SET @brand = 'XS SCUBA';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;





INSERT INTO products(product_name, sku, cost,price ,stock, barcode, is_active,eve_id, category_id) SELECT 'BCD_1', 'BCD_1',10,20,1,'BRC-BCD-1',TRUE,1, category_id FROM categories WHERE category_name = 'BCDS';
INSERT INTO products(product_name, sku, cost,price ,stock, barcode, is_active,eve_id, category_id) SELECT 'BCD_2', 'BCD_2',30,40,2,'BRC-BCD-2',TRUE,2, category_id FROM categories WHERE category_name = 'BCDS';
INSERT INTO products(product_name, sku, cost,price ,stock, barcode, is_active,eve_id, category_id) SELECT 'BCD_3', 'BCD_3',60,80,3,'BRC-BCD-3',TRUE,30, category_id FROM categories WHERE category_name = 'BCDS';

SET @sku = 'BCD_1';
SET @brand = 'HALCYON';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;


SET @sku = 'BCD_2';
SET @brand = 'DIVE RITE';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;

SET @sku = 'BCD_3';
SET @brand = 'DUI';

SELECT @supplier_id := supplier_id FROM suppliers WHERE supplier_name = @brand;
SELECT @brand_id := brand_id FROM brands WHERE brand_name = @brand;
UPDATE products SET supplier_id = @supplier_id WHERE sku =  @sku;
UPDATE products SET brand_id = @brand_id WHERE sku = @sku;

UPDATE products SET is_equipment = FALSE;
UPDATE products SET is_equipment = TRUE, is_active = FALSE where product_name LIKE '%RENT%';

INSERT INTO equipment(product_id, serial_number,eve_id,is_rental) SELECT product_id, 'SN-1234', '123',TRUE FROM products WHERE product_name lIKE '%RENT%';


CREATE TABLE sales(sale_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, customer_id VARCHAR(64) NOT NULL, register_id VARCHAR(32), employee_id VARCHAR(32), sale_date datetime, close_date DATETIME, invoice INT UNIQUE, is_service BOOLEAN, notes VARCHAR(256));


SET @cust = '';
SELECT @cust := customer_id FROM customers WHERE last_name LIKE 'TYD%';
set @emp = '';
SELECT @emp := customer_id FROM customers WHERE first_name LIKE 'RON';

INSERT INTO sales(sale_date,customer_id,employee_id, is_service,invoice) VALUES ('2025-03-03', @cust, @emp, FALSE,42);

SET @sku = 'REG_1';
SET @pro = 0;
SELECT @pro := product_id FROM products WHERE sku = @sku;
INSERT INTO sale_items(sale_id,product_id, quantity, price, cost) SELECT sale_id, @pro, 1,40,20 FROM sales;




