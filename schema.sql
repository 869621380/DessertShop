create table category
(
    name       varchar(255) not null
        primary key,
    price      double       null,
    imgURL     varchar(255) null,
    desription varchar(255) null,
    id         int          null
);

create table information
(
    username   varchar(255) null,
    action     varchar(255) null,
    time       timestamp    null,
    actionType varchar(255) null
);

create table order_details
(
    order_detail_id int auto_increment
        primary key,
    order_id        int           null,
    product_name    varchar(255)  null,
    quantity        int           null,
    price           double        null,
    img_src         varchar(255)  null,
    description     varchar(1023) null
);

create table orders
(
    order_id    int auto_increment
        primary key,
    username    varchar(255) null,
    order_date  timestamp    null,
    address     varchar(255) null,
    total_price double       null,
    status      varchar(20)  null
);

create table shopping_cart
(
    id          int auto_increment
        primary key,
    username    varchar(255) null,
    goods_name  varchar(255) null,
    description varchar(255) null,
    quantity    int          null,
    price       double       null,
    img_src     varchar(255) null
);

create table users
(
    id       int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    age      int          null,
    adress   varchar(255) null,
    phone    varchar(255) null,
    email    varchar(255) null,
    constraint `UNIQUE`
        unique (username)
);