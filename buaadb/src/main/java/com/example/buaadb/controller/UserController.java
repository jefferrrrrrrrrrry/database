package com.example.buaadb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.UserMapper;
import com.example.buaadb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            user.setSysPassword(password);
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

    @GetMapping("/user")
    public Result find(@RequestParam(defaultValue = "") String sys_username, @RequestParam(defaultValue = "-1") Integer permission,
            @RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sys_username", sys_username);
        if (permission > 0) {
            queryWrapper.eq("permission", permission);
        }
        return Result.success(PageDivision.getPage(userService.list(queryWrapper), pageNum, pageSize));
    }

    @GetMapping("/exportlog")
    public void export(HttpServletResponse response, @RequestParam String token) throws IOException {
        if (TokenUtils.getCurrentUser(token).getPermission() < 3) {
            throw new ServiceException(Status.ERROR, "无权限");
        }
        userService.export(response);
    }
}
