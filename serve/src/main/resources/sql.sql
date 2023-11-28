drop database if exists db_clue;
create database if not exists db_clue;
use db_clue;
create table tb_ssq
(
    id   int         null,
    name varchar(50) null,
    pid  int         null
);

INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (41, '河南省', null);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (4101, '郑州市', 41);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (410102, '中原区', 4101);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (410103, '二七区', 4101);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (4103, '洛阳市', 41);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (410303, '西工区', 4103);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (410305, '涧西区', 4103);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (44, '广东省', null);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (4401, '广州市', 44);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (440103, '荔湾区', 4401);
INSERT INTO db_clue.tb_ssq (id, name, pid) VALUES (440104, '越秀区', 4401);
create table tb_notify
(
    id          int auto_increment
        primary key,
    user_name   varchar(50) null,
    notify      varchar(50) null,
    create_time datetime    null
);

INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('张三', '阿松大不', '2023-11-23 11:05:05');
INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('奥斯', 'asd', '2023-11-23 11:22:17');
INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('奥斯', 'asd', '2023-11-23 11:22:28');
INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('张三', '尚未支付，涉嫌犯罪，已移送公安机关', '2023-11-23 11:26:58');
INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('奥斯', '应当或者已经通过劳动争议仲裁解决，已告知反映人', '2023-11-23 11:30:22');
INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('张三', '684', '2023-11-23 11:50:44');
INSERT INTO db_clue.tb_notify (user_name, notify, create_time) VALUES ('埃斯珀第', 'OKOKOKOKOKOKOKOKOK', '2023-11-23 16:51:11');
create table tb_forms
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO db_clue.tb_forms (name) VALUES ('人民网');
INSERT INTO db_clue.tb_forms (name) VALUES ('市区网');
INSERT INTO db_clue.tb_forms (name) VALUES ('检察网');
INSERT INTO db_clue.tb_forms (name) VALUES ('观察网');
create table tb_clue
(
    id     int auto_increment
        primary key,
    x_no   varchar(50)  null,
    x_name varchar(50)  null,
    x_from varchar(80)  null,
    ssq    varchar(225) null,
    person varchar(50)  null,
    status int          null
);

INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('001', '线索', '人民网', '河南', '张三', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('A001', 'RPG', '市区网', '广东省-广州市-荔湾区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('AS01', '奥巴', '检察网', '河南省-郑州市-二七区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('B00a', '阿索', '观察网', '河南省-洛阳市-西工区', '张三', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('A023', '澳商埠', '市区网', '广东省-广州市-荔湾区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('A037', '杀手', '市区网', '广东省-广州市-荔湾区', '张三', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('B001', '偶旁边·', '观察网', '广东省-广州市-越秀区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('FA01', '奥i三百', '检察网', '河南省-洛阳市-涧西区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('B0C7', 'u奥不是', '市区网', '河南省-洛阳市-荔湾区', '张三', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('B021', '哦啊不俗', '人民网', '河南省-郑州市-荔湾区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('H121K', '埃斯珀', '人民网', '广东省-广州市-荔湾区', '奥斯', 1);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('N91A', 'oas', '市区网', '广东省-广州市-荔湾区', '奥斯', 0);
INSERT INTO db_clue.tb_clue (x_no, x_name, x_from, ssq, person, status) VALUES ('H18V', '哦i四年', '市区网', '河南省-郑州市-undefined', '奥巴马', 0);
