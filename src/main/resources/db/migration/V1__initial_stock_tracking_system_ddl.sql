create table if not exists product
(
    product_id uuid primary key default gen_random_uuid(),
    product_name varchar(255),
    description varchar(255),
    amount integer,
    category_id uuid
);


create table if not exists category
(
    category_id uuid primary key default gen_random_uuid(),
    category_name varchar(255),
    product_id uuid
);


create table if not exists supplier
(
    supplier_id uuid primary key default gen_random_uuid(),
    supplier_name varchar(255),
    phone varchar(255),
    email varchar(255)
);


create table if not exists appUser
(
    users_id uuid primary key default gen_random_uuid(),
    name varchar(255),
    phone varchar(255),
    email varchar(255),
    status boolean,
    role varchar(255)

);


