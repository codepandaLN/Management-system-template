package com.panda.model.dto.leave;

import lombok.Data;

import java.io.Serializable;

/**
 * @author panda
 */
@Data
public class SearchLeaveByIdRequest implements Serializable {

    /**
     * 学生ID
     */
    private Integer leaveId;


    private static final long serialVersionUID = 1L;

}
