package com.panda.model.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author panda
 */
@Data
public class UserUpdatePasswordRequest {

    /**
     * 密码
     */
    private String userPassword;
}
