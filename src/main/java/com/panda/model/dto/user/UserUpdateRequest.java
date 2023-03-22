package com.panda.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新学生信息请求（仅限老师和管理员）
 *
 * @author panda
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 年级
     */
    private String userGrade;

    /**
     * 院系

     */
    private String deptId;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户角色：student / teacher / admin
     */
    private String userRole;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}