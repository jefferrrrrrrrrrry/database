package com.example.buaadb.service;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Login;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.InExport;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private LoginService loginService;
    private static final Log LOG = Log.get();

    public void login(LogInfo logInfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sys_username", logInfo.getSys_username());
        queryWrapper.eq("sys_password", logInfo.getSys_password());
        User user = null;
        Login login = new Login();
        login.setUsername(logInfo.getSys_username());
        login.setPassword(logInfo.getSys_password());
        login.setDate(new Date());
        login.setSuccess(0);
        try {
            user = getOne(queryWrapper);
        } catch (Exception e) {
            loginService.save(login);
            throw new ServiceException(Status.ERROR, "系统错误");
        }
        if (user != null) {
            logInfo.setToken(TokenUtils.genToken(logInfo.getSys_username(), logInfo.getSys_password()));
            logInfo.setPermission(user.getPermission());
        } else {
            loginService.save(login);
            throw new ServiceException(Status.ERROR, "用户名或密码错误");
        }
        login.setSuccess(1);
        loginService.save(login);
    }


    public void export(HttpServletResponse response) throws IOException {
        List<Login> list = loginService.list();
        InExport.export(response, list, "登录日志");
    }

}
