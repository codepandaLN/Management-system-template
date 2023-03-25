package com.panda.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 请假单
 * @TableName leave
 */
@TableName(value ="tb_leave")
@Data
public class LeaveVO implements Serializable {

    /**
     * 学生ID
     */
    @Schema(description = "学生ID")
    private Integer userId;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String userName;

    /**
     * 学号
     */
    @Schema(description = "学号")
    private Long sno;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Integer gender;

    /**
     * 年级
     */
    @Schema(description = "年级")
    private String userGrade;

    @Schema(description = "院系")
    private String deptId;

    /**
     * 请假原因
     */
    @Schema(description = "请假原因")
    private String leaveReason;


    /**
     * 请假天数
     */
    @Schema(description = "学生ID")
    private String leaveDays;

    /**
     * 类型：1病假，2事假
     */
    @Schema(description = "学生ID")
    private Integer leaveType;

    /**
     * 状态：1请假中，2不同意，3已同意
     */
    @Schema(description = "学生ID")
    private Integer leaveState;

    /**
     * 类型：1离校，2不离校
     */
    @Schema(description = "学生ID")
    private Integer leaveNo;

    /**
     * 创建时间
     */
    @Schema(description = "学生ID")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}