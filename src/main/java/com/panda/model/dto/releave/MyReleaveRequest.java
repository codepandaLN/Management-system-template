package com.panda.model.dto.releave;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.panda.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**查询个人销假信息
 * @TableName tb_releave
 */
@Data
public class MyReleaveRequest extends PageRequest implements Serializable {

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}