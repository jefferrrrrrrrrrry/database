package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.User;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.ClassMapper;
import com.example.buaadb.mapper.UserMapper;
import com.example.buaadb.service.ClassService;
import com.example.buaadb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LogInfo logInfo) {
        userService.login(logInfo);
        return Result.success(logInfo);
    }

    @PostMapping("/changepassword")
    public Result changepassword(@RequestBody String password) {
        User user = TokenUtils.getCurrentUser();
        user.setSys_password(password);
        userService.updateById(user);
        return Result.success();
    }

    @GetMapping("/user")
    public Result find() {
        return Result.success(userService.list());
    }
}
