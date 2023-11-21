drop database if exists db_user;
create database if not exists db_user;
use db_user;
create table tb_user
(
    id       int auto_increment
        primary key,
    name     varchar(50) null,
    age      int         null,
    sex      int         null,
    hobby    varchar(50) null,
    birthday date        null,
    bid      int         null
);

INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid) VALUES ('张三', 18, 1, '吃,喝', '2023-11-21', 1);
INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid) VALUES ('哦啊的', 14, 0, '喝,玩', '2023-11-27', 1);
create table tb_dormitory
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO db_user.tb_dormitory (name) VALUES ('801');
INSERT INTO db_user.tb_dormitory (name) VALUES ('802');
INSERT INTO db_user.tb_dormitory (name) VALUES ('803');
INSERT INTO db_user.tb_dormitory (name) VALUES ('804');
