package com.panda.model.dto.user;

import com.panda.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户查询请求
 *
 * @author panda
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

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
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    private static final long serialVersionUID = 1L;
}