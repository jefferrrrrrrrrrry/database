package com.example.buaadb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.entity.School;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.mapper.SchoolMapper;
import com.example.buaadb.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, Teacher> {

}
