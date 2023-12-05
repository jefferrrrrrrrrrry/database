package com.example.buaadb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.School;
import com.example.buaadb.entity.Student;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.mapper.SchoolMapper;
import com.example.buaadb.mapper.StudentMapper;
import com.example.buaadb.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, Teacher> {
    @Autowired
    private TeacherMapper mapper;
    public int login(LogInfo logInfo) {
        List<Teacher> teacher = mapper.login(logInfo.getUsername(), logInfo.getPassword());
        if (teacher.isEmpty()) {
            return 1; // no such student
        }
        return 0;
    }
}
