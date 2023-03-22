package com.panda.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图
 *
 * @TableName user
 */
@Data
public class UserVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 学号
     */
    private Integer sno;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年级
     */
    private String userGrade;

    /**
     * 院系

     */
    private String deptId;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户角色：student / teacher / admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;


    private static final long serialVersionUID = 1L;
}