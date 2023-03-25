package com.panda.model.dto.releave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.panda.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 销假单
 * @TableName tb_releave
 */
@Data
public class PageReleaveRequest extends PageRequest implements Serializable {
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
    @Schema(description = "创建时间")
    private Date createTime;

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

    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "name内容不正确")
    @Schema(description = "姓名")
    private String userName;

    @Min(value = 1, message = "deptId不能小于1")
    @Schema(description = "院系")
    private String deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}