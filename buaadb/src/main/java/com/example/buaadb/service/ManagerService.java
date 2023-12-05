package com.example.buaadb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Manager;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.mapper.ManagerMapper;
import com.example.buaadb.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService extends ServiceImpl<ManagerMapper, Manager> {
    @Autowired
    private ManagerMapper mapper;
    public int login(LogInfo logInfo) {
        List<Manager> manager = mapper.login(logInfo.getUsername(), logInfo.getPassword());
        if (manager.isEmpty()) {
            return 1; // no such student
        }
        return 0;
    }
}
