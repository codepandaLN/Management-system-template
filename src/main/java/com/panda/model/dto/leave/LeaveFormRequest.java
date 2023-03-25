package com.panda.model.dto.leave;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author panda
 */
@Data
public class LeaveFormRequest implements Serializable {

    /**
     * 学生ID
     */
    @Schema(description = "学生ID")
    private Integer userId;

    /**
     * 请假原因
     */
    @Schema(description = "请假原因")
    private String leaveReason;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private String endTime;
    /**
     * 请假天数
     */
    @Schema(description = "请假天数")
    private String leaveDays;

    /**
     * 类型：1病假，2事假
     */
    @Schema(description = "类型：1病假，2事假")
    private Integer leaveType;

    /**
     * 类型：1离校，2不离校
     */
    @Schema(description = "类型：1离校，2不离校")
    private Integer leaveNo;

    private static final long serialVersionUID = 1L;

}
