drop database if exists db_major;
create database if not exists  db_major;
use db_major;
create table tb_major
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO db_major.tb_major (name) VALUES ('大数据');
INSERT INTO db_major.tb_major (name) VALUES ('人工智能');
INSERT INTO db_major.tb_major (name) VALUES ('软考');
create table tb_student
(
    id       int auto_increment
        primary key,
    name     varchar(50)  null,
    age      int          null,
    sex      int          null,
    hobby    varchar(255) null,
    birthday date         null,
    mid      int          null
);

INSERT INTO db_major.tb_student (name, age, sex, hobby, birthday, mid) VALUES ('lisi', 32, 0, '乒乓球,足球', '2023-11-18', 2);
INSERT INTO db_major.tb_student (name, age, sex, hobby, birthday, mid) VALUES ('saodb1', 97, 1, '篮球,足球', '2023-11-15', 3);
INSERT INTO db_major.tb_student (name, age, sex, hobby, birthday, mid) VALUES ('lisi', 32, 0, '乒乓球,足球', '2023-11-18', 2);
INSERT INTO db_major.tb_student (name, age, sex, hobby, birthday, mid) VALUES ('saodb1', 97, 1, '篮球,足球', '2023-11-15', 3);
