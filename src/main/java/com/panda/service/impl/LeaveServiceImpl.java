package com.panda.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panda.model.entity.Leave;
import com.panda.service.LeaveService;
import com.panda.mapper.LeaveMapper;
import org.springframework.stereotype.Service;

/**
* @author 阿宁
* @description 针对表【leave(请假单)】的数据库操作Service实现
* @createDate 2023-03-18 18:37:10
*/
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave>
    implements LeaveService{

}




