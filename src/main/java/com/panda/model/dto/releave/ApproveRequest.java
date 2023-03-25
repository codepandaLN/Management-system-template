package com.panda.model.dto.releave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批销假单请求
 * @TableName tb_releave
 */
@Data
public class ApproveRequest implements Serializable {
    /**
     * 审批请假ID
     */
    @Schema(description = "审批请假ID")
    private Integer id;

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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}