package com.example.buaadb.service;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.entity.Menu;
import com.example.buaadb.entity.Role;
import com.example.buaadb.mapper.MenuMapper;
import com.example.buaadb.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
    @Autowired
    private MenuMapper mapper;
}
