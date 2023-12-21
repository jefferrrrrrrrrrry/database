package com.example.buaadb.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.User;
import com.example.buaadb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper mapper;
    private static final Log LOG = Log.get();

    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sys_username", username);
        queryWrapper.eq("sys_password", password);
        User user = null;
        try {
            user = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
        }
        return user;
    }

}
