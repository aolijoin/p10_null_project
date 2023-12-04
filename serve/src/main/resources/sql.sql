drop database if exists db_erp;
create database if not exists db_erp;
use db_erp;
create table tb_user
(
    id       int auto_increment
        primary key,
    name     varchar(50) null,
    username varchar(50) null,
    password varchar(50) null,
    type     int         null comment '1:普通账户;2:管理员'
);

INSERT INTO db_erp.tb_user (name, username, password, type) VALUES ('张三', 'zan', '123', 1);
INSERT INTO db_erp.tb_user (name, username, password, type) VALUES ('安定民', 'admin', '123', 2);
create table tb_goods
(
    id          int auto_increment
        primary key,
    goods_name  varchar(50) null,
    goods_stock int         null,
    goods_price decimal     null
);

INSERT INTO db_erp.tb_goods (goods_name, goods_stock, goods_price) VALUES ('雨伞', 80, 90);
INSERT INTO db_erp.tb_goods (goods_name, goods_stock, goods_price) VALUES ('蓝火AK47', 55, 90);
create table tb_apply
(
    id                     int auto_increment
        primary key,
    goods_name             varchar(50)  null,
    goods_amount           int          null,
    goods_id               varchar(50)  null,
    goods_applicant_id     varchar(50)  null,
    goods_applicant_name   varchar(50)  null,
    goods_applicant_status int          null comment '1代表等待审批，2代表审批通 过，3代表审批拒绝',
    examine_reason         varchar(255) null
);

INSERT INTO db_erp.tb_apply (goods_name, goods_amount, goods_id, goods_applicant_id, goods_applicant_name, goods_applicant_status, examine_reason) VALUES ('物资2', 20, 'G002', '1001', '李四', 1, null);
INSERT INTO db_erp.tb_apply (goods_name, goods_amount, goods_id, goods_applicant_id, goods_applicant_name, goods_applicant_status, examine_reason) VALUES ('物资2', 20, 'G002', '1001', '李四', 3, '物资数量不足');
INSERT INTO db_erp.tb_apply (goods_name, goods_amount, goods_id, goods_applicant_id, goods_applicant_name, goods_applicant_status, examine_reason) VALUES ('物资3', 20, 'G002', '1001', '李四', 3, '我是老壁灯');
INSERT INTO db_erp.tb_apply (goods_name, goods_amount, goods_id, goods_applicant_id, goods_applicant_name, goods_applicant_status, examine_reason) VALUES ('蓝火AK47', 90, 'G002', '1', '张三', 1, '');
INSERT INTO db_erp.tb_apply (goods_name, goods_amount, goods_id, goods_applicant_id, goods_applicant_name, goods_applicant_status, examine_reason) VALUES ('雨伞', 10, 'G002', '1', '张三', 1, '');
