package com.panda.model.dto.leave;

import com.panda.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "查询请假分页表单")
public class SearchLeaveByPageRequest extends PageRequest {


    /**
     * 学生ID
     */
    @Schema(description = "学生ID")
    private Integer userId;

//    /**
//     * 学号
//     */
//    private Long sno;
//
//    /**
//     * 性别
//     */
//    private Integer gender;
//
//    /**
//     * 年级
//     */
//    private String userGrade;
//
//    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "name内容不正确")
//    @Schema(description = "姓名")
//    private String userName;
//
//    @Min(value = 1, message = "deptId不能小于1")
//    @Schema(description = "院系")
//    private String deptId;

    @Pattern(regexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$", message = "date内容不正确")
    @Schema(description = "请假日期")
    private String date;

    @Schema(description = "请假类型")
    private Byte leaveType;

    @Schema(description = "请假状态")
    private Byte leaveState;
}
