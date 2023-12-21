package com.example.buaadb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Student;
import com.example.buaadb.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {
    @Autowired
    private StudentMapper mapper;
}
