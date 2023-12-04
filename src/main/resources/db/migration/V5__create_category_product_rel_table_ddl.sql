create table if not exists category_product_rel (
    id uuid not null primary key default gen_random_uuid(),
    category_id uuid not null,
    product_id uuid not null,
    is_active boolean default true,
    foreign key (category_id) references category (category_id),
    foreign key (product_id) references product (product_id)
);