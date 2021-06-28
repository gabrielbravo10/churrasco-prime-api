create table tb_category
(
    id_category int auto_increment
        primary key,
    name varchar(255) not null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    constraint name
        unique (name)
);

create table tb_city
(
    id_city int auto_increment
        primary key,
    name varchar(255) not null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    constraint name
        unique (name)
);

create table tb_customer
(
    id_customer int auto_increment
        primary key,
    cpf varchar(255) null,
    first_name varchar(255) not null,
    last_name varchar(255) null,
    email varchar(255) null,
    password varchar(255) null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    telefone varchar(11) null,
    constraint cpf
        unique (cpf),
    constraint email
        unique (email)
);

create table tb_address
(
    id_address int auto_increment
        primary key,
    zip_code varchar(255) null,
    street varchar(255) null,
    number varchar(255) null,
    complement varchar(255) null,
    neighborhood varchar(255) null,
    id_city int not null,
    id_customer int not null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    constraint fk_address_city
        foreign key (id_city) references tb_city (id_city),
    constraint fk_address_customer
        foreign key (id_customer) references tb_customer (id_customer)
);

create index id_city
	on tb_address (id_city);

create index id_customer
	on tb_address (id_customer);

create table tb_order
(
    id_order int auto_increment
        primary key,
    order_tracking_number varchar(255) null,
    total_price decimal(19,2) null,
    total_quantity int null,
    order_status varchar(128) null,
    order_payment varchar(128) null,
    id_customer int null,
    id_address int null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    constraint tb_order_ibfk_1
        foreign key (id_customer) references tb_customer (id_customer),
    constraint tb_order_ibfk_2
        foreign key (id_address) references tb_address (id_address)
);

create index id_address
	on tb_order (id_address);

create index id_customer
	on tb_order (id_customer);

create table tb_provider
(
    id_provider int auto_increment
        primary key,
    name varchar(255) not null,
    cnpj varchar(255) null,
    image_url varchar(255) null,
    active tinyint(1) default 0 null,
    rating decimal(6,2) null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    constraint cnpj
        unique (cnpj),
    constraint name
        unique (name)
);

create table tb_product
(
    id_product int auto_increment
        primary key,
    sku varchar(255) not null,
    name varchar(255) null,
    description varchar(255) null,
    unit_price decimal(6,2) null,
    image_url varchar(255) null,
    active tinyint(1) default 0 null,
    units_in_stock int null,
    id_provider int not null,
    date_created timestamp not null,
    date_updated timestamp null,
    date_deleted timestamp null,
    constraint fk_product_provider
        foreign key (id_provider) references tb_provider (id_provider)
);

create table tb_order_items
(
    id_order_items int auto_increment
        primary key,
    image_url varchar(255) null,
    discount decimal(6,2) null,
    price decimal(6,2) null,
    quantity int null,
    id_order int not null,
    id_product int not null,
    constraint tb_order_items_ibfk_1
        foreign key (id_order) references tb_order (id_order),
    constraint tb_order_items_ibfk_2
        foreign key (id_product) references tb_product (id_product)
);

create index id_order
	on tb_order_items (id_order);

create index id_product
	on tb_order_items (id_product);

create table tb_product_category
(
    id_product int not null,
    id_category int not null,
    primary key (id_product, id_category),
    constraint fk_product_category_category
        foreign key (id_category) references tb_category (id_category),
    constraint fk_product_category_product
        foreign key (id_product) references tb_product (id_product)
);
