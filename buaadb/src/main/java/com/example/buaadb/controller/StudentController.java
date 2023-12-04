package com.example.buaadb.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Student;
import com.example.buaadb.mapper.StudentMapper;
import com.example.buaadb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public int login(@RequestBody LogInfo logInfo) {
        return studentService.login(logInfo);
    }

    @PostMapping("/changepassword")
    public boolean changepassword() {

    }

    @PostMapping("/test")
    public String test(@RequestBody int num) {
        return "POST内容：" + num;
    }
}
