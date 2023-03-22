package com.panda.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.panda.annotation.AuthCheck;
import com.panda.constant.CommonConstant;
import com.panda.constant.UserConstant;
import com.panda.exception.ThrowUtils;
import com.panda.model.dto.user.*;
import com.panda.model.vo.LoginUserVO;
import com.panda.model.vo.UserVO;
import com.panda.common.BaseResponse;
import com.panda.common.DeleteRequest;
import com.panda.common.ErrorCode;
import com.panda.common.ResultUtils;
import com.panda.exception.BusinessException;
import com.panda.model.entity.User;
import com.panda.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author panda
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String userRole = userRegisterRequest.getUserRole();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,userRole)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword,userRole);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }


    /**
     * 添加学生(只有管理员和老师有权限)
     * 添加老师(只有管理员有权限)
     *
     * @param userAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(anyRole = {UserConstant.ADMIN_ROLE, UserConstant.TRACHER_ROLE})
    public BaseResponse<Long> addStudent(@RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        if(user.getUserRole().equals(UserConstant.TRACHER_ROLE) && !userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        String digest = DigestUtils.md5DigestAsHex((CommonConstant.SALT + user.getUserPassword()).getBytes());
        user.setUserPassword(digest);
        boolean result = userService.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(user.getId());
    }



    /**
     * 删除学生(只有管理员和老师有权限)
     *
     * @param deleteRequest
     * @param request
     * @return
     */

    @PostMapping("/delete")
    @AuthCheck(anyRole = {UserConstant.ADMIN_ROLE, UserConstant.TRACHER_ROLE})
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User user = userService.getLoginUser(request);
        if(user.getUserRole().equals(UserConstant.TRACHER_ROLE) && !userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

//    /**
//     * 删除老师(只有管理员有权限)
//     *
//     * @param deleteRequest
//     * @param request
//     * @return
//     */
//
//    @PutMapping("/delete")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Boolean> deleteTeacher(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
//        if (deleteRequest == null || deleteRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        boolean b = userService.removeById(deleteRequest.getId());
//        return ResultUtils.success(b);
//    }

    /**
     * 更新用户信息(只有管理员和老师有权限)
     *
     * @param userUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(anyRole = {UserConstant.ADMIN_ROLE, UserConstant.TRACHER_ROLE})
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        String digest = DigestUtils.md5DigestAsHex((CommonConstant.SALT + user.getUserPassword()).getBytes());
        user.setUserPassword(digest);
        boolean result = userService.updateById(user);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<UserVO> getUserById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 获取用户列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<UserVO>> listUser(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
        }

        LambdaQueryWrapper<User> queryWrapper = PagequeryResult(userQueryRequest);
//        queryWrapper.like( userQueryRequest.getUserName()!= null,User::getUserName,userQueryRequest.getUserName());
//        queryWrapper.like( userQueryRequest.getUserGrade()!= null,User::getUserGrade,userQueryRequest.getUserGrade());
//        queryWrapper.like( userQueryRequest.getDeptId()!= null,User::getDeptId,userQueryRequest.getDeptId());
//        queryWrapper.like( userQueryRequest.getPhone()!= null,User::getPhone,userQueryRequest.getPhone());
//        queryWrapper.orderByDesc(User::getUpdateTime);

        List<User> userList = userService.list(queryWrapper);
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }

    /**
     * 分页获取用户列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<UserVO>> listUserByPage(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        long current = 1;
        long size = 10;
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
            current = userQueryRequest.getCurrent();
            size = userQueryRequest.getPageSize();
        }
        LambdaQueryWrapper<User> queryWrapper = PagequeryResult(userQueryRequest);
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>(userQuery);
//        queryWrapper.like( userQueryRequest.getUserName()!= null,User::getUserName,userQueryRequest.getUserName());
//        queryWrapper.like( userQueryRequest.getUserGrade()!= null,User::getUserGrade,userQueryRequest.getUserGrade());
//        queryWrapper.like( userQueryRequest.getDeptId()!= null,User::getDeptId,userQueryRequest.getDeptId());
//        queryWrapper.like( userQueryRequest.getPhone()!= null,User::getPhone,userQueryRequest.getPhone());
//        queryWrapper.orderByDesc(User::getUpdateTime);

        Page<User> userPage = userService.page(new Page<>(current, size), queryWrapper);
        Page<UserVO> userVOPage = new PageDTO<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        List<UserVO> userVOList = userPage.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }

    /**
     * 分页条件查询公共方法（按姓名、学号、班级、学院、电话、更新时间倒序）
     * @param userQueryRequest
     * @return
     */
    public LambdaQueryWrapper<User> PagequeryResult(UserQueryRequest userQueryRequest){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like( userQueryRequest.getUserName()!= null,User::getUserName,userQueryRequest.getUserName());
        queryWrapper.like( userQueryRequest.getUserGrade()!= null,User::getUserGrade,userQueryRequest.getUserGrade());
        queryWrapper.like( userQueryRequest.getSno()!= null,User::getSno,userQueryRequest.getSno());
        queryWrapper.like( userQueryRequest.getDeptId()!= null,User::getDeptId,userQueryRequest.getDeptId());
        queryWrapper.like( userQueryRequest.getPhone()!= null,User::getPhone,userQueryRequest.getPhone());
        queryWrapper.orderByDesc(User::getUpdateTime);

        return queryWrapper;

    }


    // endregion

    /**
     * 用户更新个人信息
     *
     * @param userUpdateMyRequest
     * @param request
     * @return
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest,
                                              HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 用户修改个人密码
     * @param userUpdatePasswordRequest
     * @param request
     * @return
     */
    @PostMapping("/update/password")
    public BaseResponse<Boolean> updatePassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest,
                                                HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        String digest = DigestUtils.md5DigestAsHex((CommonConstant.SALT + userUpdatePasswordRequest.getUserPassword()).getBytes());
        loginUser.setUserPassword(digest);

        userService.updateById(loginUser);
        return ResultUtils.success(true);
    }
}
