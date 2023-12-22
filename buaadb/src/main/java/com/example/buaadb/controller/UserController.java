package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.User;
import com.example.buaadb.function.PageDivision;
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
    public Result changepassword(@RequestBody String password) { // 用户改自身的密码
        User user = TokenUtils.getCurrentUser();
        user.setSys_password(password);
        userService.updateById(user);
        return Result.success();
    }

    @PostMapping("/changeuserpassword")
    public Result changeUserPassword(@RequestBody User user) { // 管理员改密码
        user.setPermission(null);
        if (!userService.updateById(user)) {
            return Result.error("该用户不存在");
        } else {
            return Result.success();
        }
    }

    @GetMapping("/user/")
    public Result find(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        return Result.success(PageDivision.getPage(userService.list(), pageNum, pageSize));
    }
}
