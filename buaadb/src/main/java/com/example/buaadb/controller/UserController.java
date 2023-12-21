package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.User;
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
        String username = logInfo.getSys_username();
        String password = logInfo.getSys_password();
        if (false) {
            // 动作
        }
        User user = userService.login(username, password);
        return Result.success(user);
    }


}
