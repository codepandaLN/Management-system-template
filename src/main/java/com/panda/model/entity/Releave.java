package com.panda.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 销假单
 * @TableName tb_releave
 */
@TableName(value ="tb_releave")
@Data
public class Releave implements Serializable {
    /**
     * 审批请假ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生ID
     */
    private Integer userId;

    /**
     * 请假单ID
     */
    private String leaveID;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态：1销假中，2不同意，3已同意
     */
    private Integer releaveState;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}