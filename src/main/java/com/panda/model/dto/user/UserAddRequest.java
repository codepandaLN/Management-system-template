package com.panda.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户创建请求
 *
 * @author panda
 */
@Data
public class StudentAddRequest implements Serializable {

    /**
     * 学号
     */
    private Integer sno;

    /**
     * 姓名
     */
    private String name;

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
     * 密码
     */
    private String userPassword;

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

    private static final long serialVersionUID = 1L;
}