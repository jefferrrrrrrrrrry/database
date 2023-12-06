package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Student;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.mapper.StudentMapper;
import com.example.buaadb.mapper.TeacherMapper;
import com.example.buaadb.service.StudentService;
import com.example.buaadb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public int login(@RequestBody LogInfo logInfo) {
        return teacherService.login(logInfo);
    }

    @PostMapping("/changepassword")
    public boolean changepassword() {
        return false; // TODO
    }

    @GetMapping("/find")
    public Result find(@RequestParam int tno, @RequestParam String tname) {
        return Result.success(teacherMapper.find(tno, tname));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        return Result.success(teacherService.save(teacher));
    }

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Teacher> list = teacherService.list();
        teacherService.export(response, list);
        return Result.success();
    }
}
