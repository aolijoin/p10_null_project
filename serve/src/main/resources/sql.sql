drop database if exists p10_clue1;
create database if not exists p10_clue1;
use p10_clue1;
create table tb_ssq
(
    id   int primary key,
    name varchar(50) null,
    pid  int         null
);

INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (41, '河南省', null);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (44, '广东省', null);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (4101, '郑州市', 41);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (4103, '洛阳市', 41);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (4401, '广州市', 44);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (410101, '中原区', 4101);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (410102, '二七区', 4101);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (410301, '西工区', 4103);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (410302, '涧西区', 4103);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (440101, '荔湾区', 4401);
INSERT INTO p10_clue1.tb_ssq (id, name, pid)
VALUES (440102, '越秀区', 4401);

create table tb_jl
(
    id   int auto_increment
        primary key,
    name varchar(100) null
);

INSERT INTO p10_clue1.tb_jl (name)
VALUES ('经核实存在欠薪，已支付完毕');
INSERT INTO p10_clue1.tb_jl (name)
VALUES ('尚未支付，准备或已申请法院强制执行');
INSERT INTO p10_clue1.tb_jl (name)
VALUES ('尚未支付，涉嫌犯罪，已移送公安机关');
INSERT INTO p10_clue1.tb_jl (name)
VALUES ('已支付欠款或达成还款协议');
INSERT INTO p10_clue1.tb_jl (name)
VALUES ('应当或者已经通过劳动争议仲裁解决，已告知反映人');
INSERT INTO p10_clue1.tb_jl (name)
VALUES ('经核实不存在欠薪情况');
create table tb_form
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO p10_clue1.tb_form (name)
VALUES ('国家举报平台');
INSERT INTO p10_clue1.tb_form (name)
VALUES ('省级政府');
INSERT INTO p10_clue1.tb_form (name)
VALUES ('市级政府');
INSERT INTO p10_clue1.tb_form (name)
VALUES ('区级政府');
INSERT INTO p10_clue1.tb_form (name)
VALUES ('劳动监察部门');

create table tb_notify
(
    id         int auto_increment
        primary key,
    name       varchar(50) null,
    person     varchar(50) null,
    status     int         null,
    jl         int         null,
    createtime datetime    null
);

INSERT INTO p10_clue1.tb_notify (name, person, status, jl, createtime)
VALUES ('纠结啊', '翈', 2, 3, '2023-12-06 18:25:20');

create table tb_clue
(
    id     int auto_increment
        primary key,
    code   varchar(50) null,
    name   varchar(50) null,
    form   int         null,
    sheng  int         null,
    shi    int         null,
    qv     int         null,
    person varchar(50) null,
    status int         null,
    notify varchar(50) null
);

INSERT INTO p10_clue1.tb_clue (code, name, form, sheng, shi, qv, person, status, notify)
VALUES ('0001', '线索1', 1, 41, 4101, 410102, '栈撒', 0, 'asd');
INSERT INTO p10_clue1.tb_clue (code, name, form, sheng, shi, qv, person, status, notify)
VALUES ('C013', '好还是', 2, 44, 4401, 440101, '饕', 0, null);
INSERT INTO p10_clue1.tb_clue (code, name, form, sheng, shi, qv, person, status, notify)
VALUES ('J037a', '纠结啊', 5, 41, 4103, 410302, '翈', 2, '奥士第');
INSERT INTO p10_clue1.tb_clue (code, name, form, sheng, shi, qv, person, status, notify)
VALUES ('K270a', '虖', 4, 41, 4103, 410301, '耉', 1, null);
