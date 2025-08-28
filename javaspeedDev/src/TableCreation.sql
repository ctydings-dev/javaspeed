
DROP DATABASE IF EXISTS scuba;
CREATE DATABASE scuba;
USE scuba;


CREATE TABLE products(product_id INT PRIMARY KEY NOT NULL auto_increment,product_key VARCHAR(64), product_name VARCHAR(128),  sku VARCHAR(64) UNIQUE NOT NULL, category_id INT, cost DOUBLE, price DOUBLE, brand_id INT,  supplier_id INT, stock INT, barcode VARCHAR(64) UNIQUE NOT NULL, is_active BOOLEAN,  eve_id VARCHAR(64), is_equipment BOOLEAN);
CREATE TABLE equipment(category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, product_id INT, customer_id INT, serial_number VARCHAR(64), eve_id VARCHAR(64), is_rental BOOLEAN);
CREATE TABLE customers(customer_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, customer_key VARCHAR(64), customer_code  VARCHAR(128) UNIQUE,  first_name VARCHAR(64), last_name VARCHAR(64), street VARCHAR(128), city VARCHAR(64), state VARCHAR(64), country VARCHAR(64), post_code VARCHAR(64), email VARCHAR(64), phone VARCHAR(32), date_of_birth datetime, eve_id varchar(32));
CREATE TABLE registers(registers_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, register_name VARCHAR(64), outlet VARCHAR(64), register_key VARCHAR(64) NOT NULL UNIQUE);
CREATE TABLE employees(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, employee_id VARCHAR(64) UNIQUE NOT NULL, employee_name VARCHAR(64), username VARCHAR(64), email VARCHAR(64));
CREATE TABLE companies(company_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, company_name VARCHAR(128), company_key VARCHAR(62), is_supplier BOOL, company_code VARCHAR(64));
CREATE TABLE categories(category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, category_name VARCHAR(128), category_key VARCHAR(62) UNIQUE);
CREATE TABLE tags(tag_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, tag_name VARCHAR(128) UNIQUE , tag_key VARCHAR(62) UNIQUE);
CREATE TABLE product_tags(product_tag_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, product_id INT, tag_id INT);

CREATE TABLE sales(sale_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, customer_id VARCHAR(64) NOT NULL, register_id VARCHAR(32), employee_id VARCHAR(32), sale_date datetime, close_date DATETIME, invoice INT UNIQUE, is_service BOOLEAN, notes VARCHAR(128));
CREATE TABLE sale_items(sale_item_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, sale_id INT, product_id INT, quantity INT, price DOUBLE);




