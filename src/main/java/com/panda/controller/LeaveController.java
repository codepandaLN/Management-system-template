package com.panda.controller;

import com.panda.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panda
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Resource
    private UserService leaveService;

    @Resource
    private UserService userService;
}
