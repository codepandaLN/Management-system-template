package com.panda.model.dto.leave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author panda
 */
@Data
public class UpdateStateRequest implements Serializable {
    /**
     * 请假ID
     */
    @Schema(description = "请假ID")
    private Integer leaveId;
    /**
     * 状态：1请假中，2不同意，3已同意
     */
    @Schema(description = "状态：1请假中，2不同意，3已同意")
    private Integer leaveState;

    private static final long serialVersionUID = 1L;
}
