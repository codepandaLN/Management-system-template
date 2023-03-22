-- 创建库
create database leave;

-- 切换库
use leave;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别（0-男, 1-女）',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户';



DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`
(
    `leaveId`          int(11)                                            NOT NULL AUTO_INCREMENT COMMENT '请假ID',
    `userId`     int(10) UNSIGNED                                        NOT NULL COMMENT '学生ID',
    `leaveReason`      varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请假原因',
    `startTime`       datetime                                                NOT NULL COMMENT '开始时间',
    `endTime`         datetime                                                NOT NULL COMMENT '结束时间',
    `leaveDays`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '请假天数',
    `leaveType`        tinyint(3) UNSIGNED                                     NOT NULL COMMENT '类型：1病假，2事假',
    `leaveState`      tinyint(3) UNSIGNED                                     NOT NULL DEFAULT 1 COMMENT '状态：1请假中，2不同意，3已同意',
    `create_time` datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`leaveId`) USING BTREE
) comment '请假单';

