drop database if exists qutest;
create database if not exists qutest;
use qutest;
create table tb_file
(
    id   int auto_increment
        primary key,
    name varchar(255) null,
    type varchar(255) null,
    size varchar(255) null
);

INSERT INTO qutest.tb_file (name, type, size) VALUES ('关于成立成都市就业工作领导小组的通知', '.docx', '132KB');
INSERT INTO qutest.tb_file (name, type, size) VALUES ('name2', '.exle', '1.2M');
INSERT INTO qutest.tb_file (name, type, size) VALUES ('猫_你好', '.jpg', '131935');
INSERT INTO qutest.tb_file (name, type, size) VALUES ('猫', '.jpg', '78894');
INSERT INTO qutest.tb_file (name, type, size) VALUES ('猫_吐舌', '.jpg', '82288');
INSERT INTO qutest.tb_file (name, type, size) VALUES ('猫_吐舌', '.jpg', '82288');
create table tb_goods
(
    id       int auto_increment
        primary key,
    name     varchar(50) null,
    province varchar(50) null comment '来源',
    city     varchar(50) null comment '文件提名',
    address  varchar(50) null comment '全宗号',
    year     varchar(50) null comment '年度',
    dh       varchar(20) null,
    wh       varchar(50) null comment '文号',
    zrz      varchar(50) null comment '责任人',
    zdrg     date        null comment '归档日期',
    mj       varchar(50) null comment '密级',
    bgqx     varchar(50) null comment '保管期限'
);

INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('1', 'OA归档文件1', '关于成立成都市就业工作领导小组的通知22', '1001', '2021', '0110-WS·2021-Y-BGS-0', '省办公厅[2021]001号', '张三', '2021-01-01', '机密', '永久');
INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('2', 'OA归档文件1', '神经病医院', '1001', '2021', '0110-WS·2021-Y-BGS-0', '省办公厅[2021]001号', '张三', '2015-05-01', '机密', '永久');
INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('1', 'OA归档文件1', '精神病医院2', '1001', '2021', '0110-WS·2021-Y-BGS-0', '省办公厅[2021]001号', '张三', '2003-01-01', '机密', '永久');
INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('1', '公司', 'timing', 'zonghao', '2023', 'danghao', 'wenhao', '热恩人', '2023-12-06', '绝密', '10年');
INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('4', '公司', 'timing', 'zonghao', '2023', 'danghao', 'wenhao', '热恩人', '2023-12-06', '绝密', '10年');
INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('4', '公司', 'asd', 'zonghao', '2022', 'asd', 'avad', '凹版', '2023-12-30', '机密', '10年');
INSERT INTO qutest.tb_goods (name, province, city, address, year, dh, wh, zrz, zdrg, mj, bgqx) VALUES ('3', 's', 'oooooooooooooo', '1325', '2021', 'asd', 'asdjpinoa', 'zhosa', '2023-12-21', '绝密', '30年');
create table tb_status
(
    id     int auto_increment
        primary key,
    status varchar(50) null
);

INSERT INTO qutest.tb_status (status) VALUES ('收集中');
INSERT INTO qutest.tb_status (status) VALUES ('归档');
INSERT INTO qutest.tb_status (status) VALUES ('新建');
INSERT INTO qutest.tb_status (status) VALUES ('已保存');
