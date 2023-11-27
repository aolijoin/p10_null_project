drop database if exists db_user_book_borrow;
create database if not exists db_user_book_borrow;
use db_user_book_borrow;
create table tb_book
(
    id     int auto_increment
        primary key,
    name   varchar(50) null comment '书名',
    pid    int         null comment '出版社外键',
    author varchar(50) null comment '作者',
    num    int         null comment '次数',
    status int         null,
    del    int         null
);

INSERT INTO db_user_book_borrow.tb_book (name, pid, author, num, status, del)
VALUES ('三国演义', 3, '罗贯中', 102, 1, 0);
INSERT INTO db_user_book_borrow.tb_book (name, pid, author, num, status, del)
VALUES ('水浒传', 2, '施耐淹', 55, 1, 0);
INSERT INTO db_user_book_borrow.tb_book (name, pid, author, num, status, del)
VALUES ('西游记', 1, '吴承恩', 101, 1, 0);
INSERT INTO db_user_book_borrow.tb_book (name, pid, author, num, status, del)
VALUES ('红楼梦', 1, '曹雪芹', 50, 1, 0);
create table tb_borrow
(
    id          int auto_increment
        primary key,
    bid         int      not null,
    uid         int      not null,
    create_time datetime null,
    end_time    datetime null
);

INSERT INTO db_user_book_borrow.tb_borrow (bid, uid, create_time, end_time)
VALUES (1, 1, '2023-11-27 18:37:10', '2023-11-27 19:15:00');
INSERT INTO db_user_book_borrow.tb_borrow (bid, uid, create_time, end_time)
VALUES (1, 1, '2023-11-27 19:53:26', '2023-11-27 19:53:37');
INSERT INTO db_user_book_borrow.tb_borrow (bid, uid, create_time, end_time)
VALUES (3, 3, '2023-11-27 21:22:40', '2023-11-27 21:44:24');
INSERT INTO db_user_book_borrow.tb_borrow (bid, uid, create_time, end_time)
VALUES (2, 1, '2023-11-27 21:23:10', '2023-11-27 21:35:07');
INSERT INTO db_user_book_borrow.tb_borrow (bid, uid, create_time, end_time)
VALUES (2, 1, '2023-11-27 21:38:35', '2023-11-27 21:40:16');
INSERT INTO db_user_book_borrow.tb_borrow (bid, uid, create_time, end_time)
VALUES (1, 3, '2023-11-27 21:46:16', '2023-11-27 21:46:49');
create table tb_publishing
(
    id   int auto_increment
        primary key,
    name varchar(100) null
);

INSERT INTO db_user_book_borrow.tb_publishing (name)
VALUES ('人民出版社');
INSERT INTO db_user_book_borrow.tb_publishing (name)
VALUES ('清华出版社');
INSERT INTO db_user_book_borrow.tb_publishing (name)
VALUES ('机械工业出版社');
create table tb_user
(
    id       int auto_increment
        primary key,
    name     varchar(50) null,
    username varchar(50) null,
    password varchar(50) null
);

INSERT INTO db_user_book_borrow.tb_user (name, username, password)
VALUES ('张三', 'zan', '123');
INSERT INTO db_user_book_borrow.tb_user (name, username, password)
VALUES ('管理员', 'admin', '123');
INSERT INTO db_user_book_borrow.tb_user (name, username, password)
VALUES ('李四', 'li', '123');
