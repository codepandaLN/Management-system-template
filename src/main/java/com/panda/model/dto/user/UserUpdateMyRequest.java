package com.panda.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新个人信息请求
 *
 * @author panda
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 学号
     */
    private Long sno;

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
     * 用户简介
     */
    private String userProfile;




    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}