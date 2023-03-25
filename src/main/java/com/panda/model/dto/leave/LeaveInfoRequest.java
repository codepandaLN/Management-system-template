package com.panda.model.dto.leave;

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
 * 查询个人请假信息
 * @TableName leave
 */
@TableName(value ="tb_leave")
@Data
public class LeaveInfoRequest extends PageRequest implements Serializable {

    /**
     * 学生ID
     */
    @Schema(description = "学生ID")
    private Integer userId;


    /**
     * 状态：1请假中，2不同意，3已同意
     */
    @Schema(description = "状态：1请假中，2不同意，3已同意")
    private Integer leaveState;


    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}