package com.example.buaadb.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
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
        try {
            User user = TokenUtils.getCurrentUser();
            user.setSys_password(password);
            userService.updateById(user);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/changeuserpassword")
    public Result changeUserPassword(@RequestBody User user) { // 管理员改密码
        user.setPermission(null);
        try {
            return Result.success(userService.updateById(user));
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @GetMapping("/user/")
    public Result find(@RequestParam String name, @RequestParam Integer permission,
            @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sys_username", name);
        if(permission != null) {
            queryWrapper.eq("permission", permission);
        }
        return Result.success(PageDivision.getPage(userService.list(queryWrapper), pageNum, pageSize));
    }
}
