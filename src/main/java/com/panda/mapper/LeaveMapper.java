package com.panda.mapper;

import com.panda.model.entity.Leave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author panda
* @description 针对表【leave(请假单)】的数据库操作Mapper
* @createDate 2023-03-18 18:37:10
* @Entity com.panda.model.entity.Leave
*/
public interface LeaveMapper extends BaseMapper<Leave> {
    /**
     *查询请假分页数据
     * @param param
     * @return
     */
    public ArrayList<HashMap> searchLeaveByPage(HashMap param);

    public long searchLeaveCount(HashMap param);

    public long searchContradiction(HashMap param);

    public int deleteLeaveById(HashMap param);




}




