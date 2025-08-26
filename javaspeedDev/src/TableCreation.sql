
DROP DATABASE scuba;
CREATE DATABASE scuba;

USE scuba;

CREATE TABLE products(product_id INT PRIMARY KEY NOT NULL auto_increment,product_key VARCHAR(64), product_name VARCHAR(128),  sku VARCHAR(64), category_id INT, cost DOUBLE, price DOUBLE, brand_id INT,  supplier_id INT, stock INT, barcode VARCHAR(64), is_active BOOLEAN, serial_number VARCHAR(128));
CREATE TABLE equipments(category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, item_id INT, serial_number VARCHAR(64));
CREATE TABLE persons(person_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, customer_id VARCHAR(64), customer_code  VARCHAR(128),  first_name VARCHAR(64), last_name VARCHAR(64), street VARCHAR(128), city VARCHAR(64), state VARCHAR(64), country VARCHAR(64), post_code VARCHAR(64), email VARCHAR(64), phone VARCHAR(32), date_of_birth datetime, eve_id varchar(32));
CREATE TABLE registers(registers_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, register_name VARCHAR(64), outlet VARCHAR(64), register_id VARCHAR(64) NOT NULL UNIQUE);
CREATE TABLE employees(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, employee_id VARCHAR(64) UNIQUE NOT NULL, employee_name VARCHAR(64), username VARCHAR(64), email VARCHAR(64));
CREATE TABLE companies(company_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, company_name VARCHAR(128), company_key VARCHAR(62) UNIQUE, is_supplier BOOL, company_code VARCHAR(64));
CREATE TABLE categories(category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, category_name VARCHAR(128), category_key VARCHAR(62) UNIQUE);
CREATE TABLE tags(tag_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, tag_name VARCHAR(128) UNIQUE , tag_key VARCHAR(62) UNIQUE);
CREATE TABLE product_tags(product_tag_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, product_id INT, tag_id INT);

CREATE TABLE sales(sale_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, customer_id VARCHAR(64) NOT NULL, register_id VARCHAR(32), employee_id VARCHAR(32), sale_date datetime, invoice VARCHAR(32), service_id INT);
CREATE TABLE sale_items(sale_item_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, sales_id INT, product_id INT, amount INT);




