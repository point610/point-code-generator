# 数据库初始化

-- 创建库
create database if not exists code_generator_db;

-- 切换库
use code_generator_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';


-- 代码生成器表
create table if not exists generator
(
    id          bigint auto_increment comment 'id' primary key,
    name        varchar(128)                       null comment '名称',
    description text                               null comment '描述',
    basePackage varchar(128)                       null comment '基础包',
    version     varchar(128)                       null comment '版本',
    author      varchar(128)                       null comment '作者',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    picture     varchar(256)                       null comment '图片',
    fileConfig  text                               null comment '文件配置（json字符串）',
    modelConfig text                               null comment '模型配置（json字符串）',
    distPath    text                               null comment '代码生成器产物路径',
    status      int      default 0                 not null comment '状态',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '代码生成器' collate = utf8mb4_unicode_ci;

-- 模拟用户数据
INSERT INTO code_generator_db.user (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole)
VALUES (1, 'point', 'b0dd3697a192885d7c055db46155b26a', 'point_master',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '啦啦啦', 'admin');
INSERT INTO code_generator_db.user (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole)
VALUES (2, 'point_2', 'b0dd3697a192885d7c055db46155b26a', 'point_working',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '啦啦啦', 'user');

-- 模拟代码生成器数据
INSERT INTO code_generator_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig,
                                         modelConfig, distPath, status, userId)
VALUES (1, 'ACM 模板项目', 'ACM 模板项目生成器', 'com.point', '1.0', 'point', '["Java"]',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '{}', '{}', null, 0, 1);
INSERT INTO code_generator_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig,
                                         modelConfig, distPath, status, userId)
VALUES (2, 'Spring Boot 初始化模板', 'Spring Boot 初始化模板项目生成器', 'com.point', '1.0', 'point', '["Java"]',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '{}', '{}', null, 0, 1);
INSERT INTO code_generator_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig,
                                         modelConfig, distPath, status, userId)
VALUES (3, 'point外卖', 'point外卖项目生成器', 'com.point', '1.0', 'point', '["Java", "前端"]',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '{}', '{}', null, 0, 1);
INSERT INTO code_generator_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig,
                                         modelConfig, distPath, status, userId)
VALUES (4, 'point用户中心', 'point用户中心项目生成器', 'com.point', '1.0', 'point', '["Java", "前端"]',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '{}', '{}', null, 0, 1);
INSERT INTO code_generator_db.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig,
                                         modelConfig, distPath, status, userId)
VALUES (5, 'point商城', 'point商城项目生成器', 'com.point', '1.0', 'point', '["Java", "前端"]',
        'https://i.ibb.co/dDP9Fn2/user-avatar-1752189699880771586-Ng3fy-ANH-OIP-2-jpg8111768285787531669.jpg', '{}', '{}', null, 0, 1);

use code_generator_db;

delete from generator where id>6;