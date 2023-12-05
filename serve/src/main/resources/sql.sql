drop database if exists db_user;
create database if not exists db_user;
use db_user;
create table tb_dormitory
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO db_user.tb_dormitory (name)
VALUES ('801');
INSERT INTO db_user.tb_dormitory (name)
VALUES ('802');
INSERT INTO db_user.tb_dormitory (name)
VALUES ('803');
INSERT INTO db_user.tb_dormitory (name)
VALUES ('804');

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

INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid)
VALUES ('心眼巨多-安倍晋三', 18, 0, '吃,喝', '2024-10-31', 1);
INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid)
VALUES ('脑洞大开-肯尼迪', 14, 0, '喝,玩,吃', '2023-09-29', 2);
INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid)
VALUES ('卡扎菲大佐', 35, 0, '玩,喝,吃', '2009-12-16', 3);
INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid)
VALUES ('SpringB00t', 320, 1, '喝,玩', '2023-12-26', 4);
INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid)
VALUES ('奥巴马', 35, 1, '喝,玩', '2009-12-16', 1);
INSERT INTO db_user.tb_user (name, age, sex, hobby, birthday, bid)
VALUES ('迪奥布兰度', 18, 0, '吃,玩,喝', '2023-12-30', 3);
