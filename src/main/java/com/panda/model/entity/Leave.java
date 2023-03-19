package com.panda.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 请假单
 * @TableName leave
 */
@TableName(value ="leave")
@Data
public class Leave implements Serializable {
    /**
     * 请假ID
     */
    @TableId(type = IdType.AUTO)
    private Integer leaveId;

    /**
     * 学生ID
     */
    private Integer userId;

    /**
     * 请假原因
     */
    private String leaveReason;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 请假天数
     */
    private String leaveDays;

    /**
     * 类型：1病假，2事假
     */
    private Integer leaveType;

    /**
     * 状态：1请假中，2不同意，3已同意
     */
    private Integer leaveState;

    /**
     * 创建时间
     */
    private Date create_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}