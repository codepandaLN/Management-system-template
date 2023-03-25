package com.panda.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 销假单
 * @TableName tb_releave
 */
@TableName(value ="tb_releave")
@Data
public class ReleaveVo implements Serializable {

    /**
     * 审批请假ID
     */
    @Schema(description = "审批请假ID")
    private Integer id;

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
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;

    /**
     * 状态：1销假中，2不同意，3已同意
     */
    @Schema(description = "状态：1销假中，2不同意，3已同意")
    private Integer releaveState;

    /**
     * 创建时间
     */
    @Schema(description = "学生ID")
    private Date createTime;


    /**
     * 请假原因
     */
    @Schema(description = "请假原因")
    private String leaveReason;


    /**
     * 类型：1病假，2事假
     */
    @Schema(description = "类型：1病假，2事假")
    private Integer leaveType;

    /**
     * 状态：1请假中，2不同意，3已同意
     */
    @Schema(description = "状态：1请假中，2不同意，3已同意")
    private Integer leaveState;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}