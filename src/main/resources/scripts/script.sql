drop database if exists churrasco_prime;
create database churrasco_prime;
use churrasco_prime;

DROP TABLE IF EXISTS tb_provider;
CREATE TABLE tb_provider(
    id_provider int AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    cnpj varchar(255) DEFAULT NULL,
    image_url varchar(255) DEFAULT NULL,
    active bit(1) DEFAULT b'1',
    rating decimal(6,2) DEFAULT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_provider),
    UNIQUE KEY (name)
);

DROP TABLE IF EXISTS tb_category;
create table tb_category(
    id_category int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_category),
    UNIQUE KEY (name)
);

DROP TABLE IF EXISTS tb_product;
CREATE TABLE tb_product(
    id_product int AUTO_INCREMENT,
    sku varchar(255) NOT NULL,
    name varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    unit_price decimal(6,2) DEFAULT NULL,
    image_url varchar(255) DEFAULT NULL,
    active bit(1) DEFAULT b'1',
    units_in_stock int DEFAULT NULL,
    id_provider int NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_product),
    FOREIGN KEY (id_provider) REFERENCES tb_provider(id_provider)
);

DROP TABLE IF EXISTS tb_city;
CREATE TABLE tb_city (
    id_city int AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_city),
    UNIQUE KEY (name)
);

DROP TABLE IF EXISTS tb_customer;
CREATE TABLE tb_customer(
    id_customer int AUTO_INCREMENT,
    cpf varchar(255) DEFAULT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_customer),
    UNIQUE KEY (email),
    UNIQUE KEY (cpf)
);

DROP TABLE IF EXISTS tb_address;
CREATE TABLE tb_address(
    id_address int AUTO_INCREMENT,
    zip_code varchar(255) DEFAULT NULL,
    street varchar(255) DEFAULT NULL,
    number varchar(255) DEFAULT NULL,
    complement varchar(255) DEFAULT NULL,
    neighborhood varchar(255) DEFAULT NULL,
    id_city int NOT NULL,
    id_customer int NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_address),
    KEY (id_city),
    KEY (id_customer),
    FOREIGN KEY (id_city) REFERENCES tb_city(id_city),
    FOREIGN KEY (id_customer) REFERENCES tb_customer(id_customer)
);

DROP TABLE IF EXISTS tb_telephone;
CREATE TABLE tb_telephone(
    id_telephone int AUTO_INCREMENT,
    telephone_number varchar(255) NOT NULL,
    id_customer int NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_telephone),
    KEY (id_customer),
    FOREIGN KEY (id_customer) REFERENCES tb_customer(id_customer)
);

DROP TABLE IF EXISTS tb_product_category;
CREATE TABLE tb_product_category(
    id_product int NOT NULL,
    id_category int NOT NULL,
    KEY (id_product),
    KEY (id_category),
    FOREIGN KEY (id_product) REFERENCES tb_product (id_product),
    FOREIGN KEY (id_category) REFERENCES tb_category (id_category)
);

DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order(
    id_order int  AUTO_INCREMENT,
    order_tracking_number varchar(255) DEFAULT NULL,
    total_price decimal(19,2) DEFAULT NULL,
    total_quantity int DEFAULT NULL,
    order_status varchar(128) DEFAULT NULL,
    order_payment varchar(128) DEFAULT NULL,
    rating decimal(6,2) DEFAULT NULL,
    id_customer int DEFAULT NULL,
    id_address int DEFAULT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id_order),
    KEY (id_customer),
    KEY (id_address),
    FOREIGN KEY (id_customer) REFERENCES tb_customer(id_customer),
    FOREIGN KEY (id_address) REFERENCES tb_address(id_address)
);

DROP TABLE IF EXISTS tb_order_items;
CREATE TABLE tb_order_items(
    id_order_items int auto_increment,
    image_url varchar(255) DEFAULT NULL,
    discount decimal(6,2) DEFAULT NULL,
    price decimal(6,2) DEFAULT NULL,
    quantity int DEFAULT NULL,
    id_order int NOT NULL,
    id_product int NOT NULL,
    PRIMARY KEY (id_order_items),
    KEY (id_product),
    FOREIGN KEY (id_order) REFERENCES tb_order (id_order),
    FOREIGN KEY (id_product) REFERENCES tb_product (id_product)
);

DROP TABLE IF EXISTS tb_delivery;
CREATE TABLE tb_delivery(
    id int AUTO_INCREMENT,
    delivery_number varchar(255) DEFAULT NULL,
    departure datetime(6) DEFAULT NULL,
    arrival datetime(6) DEFAULT NULL,
    rating decimal(6,2) DEFAULT NULL,
    id_order int NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    date_deleted timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (id_order) REFERENCES tb_order (id_order)
);
