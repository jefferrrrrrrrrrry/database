package com.example.buaadb.service;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.common.Status;
import com.example.buaadb.entity.Role;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.RoleMapper;
import com.example.buaadb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    @Autowired
    private RoleMapper mapper;
    private static final Log LOG = Log.get();
}
