package com.panda.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.panda.annotation.AuthCheck;
import com.panda.common.BaseResponse;
import com.panda.common.ErrorCode;
import com.panda.common.ResultUtils;
import com.panda.constant.UserConstant;
import com.panda.mapper.ReleaveMapper;
import com.panda.model.dto.leave.LeaveInfoRequest;
import com.panda.model.dto.releave.ApproveRequest;
import com.panda.model.dto.releave.MyReleaveRequest;
import com.panda.model.dto.releave.PageReleaveRequest;
import com.panda.model.dto.releave.ReleaveRequest;
import com.panda.model.entity.Leave;
import com.panda.model.entity.Releave;
import com.panda.model.entity.User;
import com.panda.model.vo.LeaveVO;
import com.panda.model.vo.PageReleaveVo;
import com.panda.model.vo.ReleaveVo;
import com.panda.service.LeaveService;
import com.panda.service.ReleaveService;
import com.panda.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author panda
 */
@RestController
@RequestMapping("/releave")
public class ReleaveController {

    @Resource
    private ReleaveService releaveService;
    @Resource
    private ReleaveMapper releaveMapper;
    @Resource
    private UserService userService;
    @Resource
    private LeaveService leaveService;


    @PostMapping("/submit")
    @Operation(summary = "添加销假申请")
    public BaseResponse<Boolean> insert(@RequestBody ReleaveRequest form){
        Leave leave = leaveService.getById(form.getLeaveID());
        if(leave.getLeaveState()!=3){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }

        Releave releave = new Releave();
        releave.setUserId(form.getUserId());
        releave.setLeaveID(form.getLeaveID());
        releave.setReleaveState(1);
        if(releaveService.save(releave)){
            return ResultUtils.success(true);
        }
        return ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @PostMapping("/approve")
    @Operation(summary = "审批销假申请")
    @AuthCheck(mustRole = UserConstant.TRACHER_ROLE)
    public BaseResponse<Boolean> approve(@RequestBody ApproveRequest form){
        Releave releave  = new Releave();
        releave.setId(form.getId());
        releave.setReleaveState(form.getReleaveState());
        releave.setRemarks(form.getRemarks());

        if(releaveService.updateById(releave)){
            return ResultUtils.success(true);
        }

        return ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    //学生获取自己的销假信息
    @PostMapping("/list/me")
    @Operation(summary = "学生获取自己的销假信息")
    public BaseResponse<Page<ReleaveVo>> getLeaveMeInfo(@RequestBody MyReleaveRequest info){

        QueryWrapper<Releave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", info.getUserId());
        queryWrapper.orderByAsc("releaveState");
        queryWrapper.orderByDesc("createTime");


        Page<Releave> releavePage = releaveMapper.selectPage(new Page<>(info.getCurrent(), info.getPageSize()), queryWrapper);
        Page<ReleaveVo> releaveVOPage = new PageDTO<>(info.getCurrent(), info.getPageSize());

        List<ReleaveVo> leaveVOList = releavePage.getRecords().stream().map(leave -> {
            ReleaveVo releaveVO = new ReleaveVo();
            BeanUtils.copyProperties(leave, releaveVO);

            String leaveId = leave.getLeaveID();
            Leave byId = leaveService.getById(leaveId);

            if(byId != null){
                releaveVO.setLeaveReason(byId.getLeaveReason());
                releaveVO.setLeaveType(byId.getLeaveType());
                releaveVO.setLeaveState(byId.getLeaveState());
            }

            return releaveVO;
        }).collect(Collectors.toList());

        releaveVOPage.setRecords(leaveVOList);
        return ResultUtils.success(releaveVOPage);

    }

    //老师获取销假信息
    @PostMapping("/list/page")
    @Operation(summary = "老师获取销假信息")
    @AuthCheck(anyRole = {UserConstant.ADMIN_ROLE, UserConstant.TRACHER_ROLE})
    public BaseResponse<Page<PageReleaveVo>> getLeaveInfo(@RequestBody PageReleaveRequest info){

        QueryWrapper<Releave> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("releaveState");
        queryWrapper.orderByDesc("createTime");


        Page<Releave> releavePage = releaveMapper.selectPage(new Page<>(info.getCurrent(), info.getPageSize()), queryWrapper);
        Page<PageReleaveVo> releaveVOPage = new PageDTO<>(info.getCurrent(), info.getPageSize());

        List<PageReleaveVo> leaveVOList = releavePage.getRecords().stream().map(leave -> {
            PageReleaveVo releaveVO = new PageReleaveVo();
            BeanUtils.copyProperties(leave, releaveVO);

            String leaveId = leave.getLeaveID();
            Leave byId = leaveService.getById(leaveId);

            if(byId != null){
                releaveVO.setLeaveReason(byId.getLeaveReason());
                releaveVO.setLeaveType(byId.getLeaveType());
                releaveVO.setLeaveState(byId.getLeaveState());
            }

            Integer userId = leave.getUserId();
            User user = userService.getById(userId);

            if(user != null){
                releaveVO.setUserName(user.getUserName());
                releaveVO.setSno(user.getSno());
                releaveVO.setGender(user.getGender());
                releaveVO.setDeptId(user.getDeptId());
                releaveVO.setUserGrade(user.getUserGrade());
            }

            return releaveVO;
        }).collect(Collectors.toList());

        releaveVOPage.setRecords(leaveVOList);
        return ResultUtils.success(releaveVOPage);

    }


}
