create table t_department
(
    id          int auto_increment
        primary key,
    name        varchar(255)                        null comment '部门名称',
    user_id     int                                 null comment '部门主管id',
    create_time timestamp default CURRENT_TIMESTAMP null
)
    comment '部门表';

create table t_role
(
    id        int auto_increment comment '权限编号'
        primary key,
    role_code varchar(255) null comment '权限字符',
    role_name varchar(255) null comment '角色名称'
)
    comment '角色表';

create table t_user
(
    id            int auto_increment
        primary key,
    no            varchar(255)                        null comment '工号',
    role_id       int                                 null comment '角色id',
    username      varchar(255)                        not null comment '姓名',
    phone         varchar(255)                        null comment '手机号码',
    email         varchar(255)                        null comment '邮箱',
    seat          varchar(255)                        null comment '座位',
    status        int                                 null comment '0-离职 1-在职 2-实习生',
    department_id int                                 null comment '部门id',
    create_time   timestamp default CURRENT_TIMESTAMP null,
    password      varchar(255)                        null comment '密码'
)
    comment '用户表';

