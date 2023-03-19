package com.panda.constant;

/**
 * 用户常量
 *
 * @author panda
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 系统用户 id（虚拟用户）
     */
    long SYSTEM_USER_ID = 0;

    //  region 权限

    /**
     * 默认权限:学生
     */
    String DEFAULT_ROLE = "student";

    /**
     * 老师权限
     */
    String TRACHER_ROLE = "teacher";

    /**
     * 管理员权限
     */
    String ADMIN_ROLE = "admin";


    // endregion
}
