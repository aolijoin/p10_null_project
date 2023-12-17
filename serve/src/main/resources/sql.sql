drop database if exists day_test;
create database if not exists day_test;
use day_test;
create table tb_major
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO day_test.tb_major (name) VALUES ('人工智能');
INSERT INTO day_test.tb_major (name) VALUES ('大数据');
INSERT INTO day_test.tb_major (name) VALUES ('全栈');
create table tb_student
(
    id        int auto_increment
        primary key,
    name      varchar(50)                  null,
    person_id varchar(20)                  null,
    mid       int                          null,
    phone     varchar(20)                  null,
    password  varchar(50) default '123456' null,
    status    int         default 0        null,
    del       int         default 0        null
);

INSERT INTO day_test.tb_student (name, person_id, mid, phone, password, status, del) VALUES ('张三', '4114222200302184524', 1, '18790717524', '123456', 0, 0);
INSERT INTO day_test.tb_student (name, person_id, mid, phone, password, status, del) VALUES ('只能怪', '41894651328465', 2, '123489656', '123456', 0, 0);
create table tb_user
(
    id       int auto_increment
        primary key,
    name     varchar(50) null,
    username varchar(50) null,
    password varchar(50) null
);

INSERT INTO day_test.tb_user (name, username, password) VALUES ('管理员', 'admin', '123');
