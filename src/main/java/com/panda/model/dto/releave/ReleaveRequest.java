package com.panda.model.dto.releave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.panda.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 销假申请
 * @TableName tb_releave
 */
@Data
public class ReleaveRequest implements Serializable {

    /**
     * 学生ID
     */
    @Schema(description = "学生ID")
    private Integer userId;

    /**
     * 请假单ID
     */
    @Schema(description = "请假单ID")
    private String leaveID;

    /**
     * 状态：1销假中，2不同意，3已同意
     */
    @Schema(description = "状态：1销假中，2不同意，3已同意")
    private Integer releaveState;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}