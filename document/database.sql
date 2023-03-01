create database  user_manager;
use user_manager;
create table t_user_info
(
    id           int auto_increment
        primary key,
    name         varchar(50)                         null comment '姓名',
    age          int                                 null comment '年龄',
    sex          bit                                 null comment '性别 1-男 0-女',
    phone        varchar(30)                         null comment '联系电话',
    address      varchar(255)                        null comment '详细地址',
    created_time timestamp default CURRENT_TIMESTAMP null,
    deleted      int       default 0                 null comment '1-删除',
    area_code    varchar(10)                         null comment '地址编码'
)
    comment '用户表';

create table t_user_info_history
(
    id           int auto_increment
        primary key,
    user_id      int                                 null,
    name         varchar(50)                         null comment '名称',
    age          int                                 null comment '年龄',
    phone        varchar(30)                         null comment '手机',
    address      varchar(255)                        null comment '地址',
    created_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    operate_type varchar(20)                         null comment 'update-更新 delete-删除',
    area_code    varchar(10)                         null,
    constraint t_user_info_history_t_user_info_fk_user_id
        foreign key (user_id) references t_user_info (id)
)
    comment '用户信息更新历史';

