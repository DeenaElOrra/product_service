CREATE TABLE product.product
(
    id varchar(36) NOT NULL,
    name varchar(255) NOT NULL,
    price decimal(10,2) NOT NULL,
    unit varchar(20) NOT NULL,
    CONSTRAINT product_primary_key PRIMARY KEY (id)
);