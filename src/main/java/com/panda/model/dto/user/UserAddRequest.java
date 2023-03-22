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
public class UserAddRequest implements Serializable {

    /**
     * 姓名
     */
    private String userName;


    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户角色：student / teacher / admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}