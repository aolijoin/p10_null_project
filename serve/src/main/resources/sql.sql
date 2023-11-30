drop database if exists db_goods;
create database if not exists db_goods;
use db_goods;
create table tb_cart
(
    id  int auto_increment
        primary key,
    gid int null,
    num int null
);

INSERT INTO db_goods.tb_cart (gid, num)
VALUES (3, 1);
INSERT INTO db_goods.tb_cart (gid, num)
VALUES (4, 3);

create table tb_category
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO db_goods.tb_category (name)
VALUES ('家电');
INSERT INTO db_goods.tb_category (name)
VALUES ('电子产品');
INSERT INTO db_goods.tb_category (name)
VALUES ('军火武器');
create table tb_goods
(
    id    int auto_increment
        primary key,
    name  varchar(50) null,
    price decimal     null,
    num   int         null,
    cid   int         null
);

INSERT INTO db_goods.tb_goods (name, price, num, cid)
VALUES ('AK47', 88, 0, 3);
INSERT INTO db_goods.tb_goods (name, price, num, cid)
VALUES ('电视机', 4444, 8, 1);
INSERT INTO db_goods.tb_goods (name, price, num, cid)
VALUES ('RPG', 234, 0, 3);
INSERT INTO db_goods.tb_goods (name, price, num, cid)
VALUES ('宇宙大爆炸', 11, 47, 2);
create table tb_order
(
    id          int auto_increment
        primary key,
    content     varchar(255) null,
    total_price varchar(100) null,
    create_time datetime     null
);

INSERT INTO db_goods.tb_order (content, total_price, create_time)
VALUES ('电视机 1个. RPG 3个. AK47 2个. ', '5322', '2023-11-30 16:07:18');

