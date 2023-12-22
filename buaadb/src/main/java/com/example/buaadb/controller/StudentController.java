package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Student;
import com.example.buaadb.entity.User;
import com.example.buaadb.function.InExport;
import com.example.buaadb.mapper.StudentMapper;
import com.example.buaadb.service.StudentService;
import com.example.buaadb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private UserService userService;

    @GetMapping("/selectCourse")
    public Result selectCourse(@RequestParam String sno,
                               @RequestParam String cno){
        studentMapper.insert(sno, cno);
        return Result.success();
    }
    @PostMapping("/changepassword")
    public boolean changepassword() {
        return false; // TODO
    }

    @GetMapping("/findStudent")
    public Result findStudent(@RequestParam String sno, @RequestParam String sname) {
        return Result.success(studentMapper.findStudent(sno, sname));
    }

    @GetMapping("/findCourse")
    public Result findCourse(@RequestParam String sno){
        return Result.success(studentMapper.findCourse(sno));
    }

    @PostMapping("/test")
    public Result test(@RequestBody int num) {
        return Result.success("POST内容：" + num);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        if (userService.getById(student.getSno()) != null) {
            return Result.error(Status.ERROR, "添加失败，用户名已存在");
        } else {
            userService.save(new User(student.getSno(), student.getSpassword(), 1));
            return Result.success(studentService.save(student));
        }
    }

    @DeleteMapping("/{sno}")
    public Result del(@PathVariable String sno) {
        studentService.removeById(sno);
        userService.removeById(sno);
        return Result.success();
    }
    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        return Result.success(studentService.updateById(student));
    }


    @PostMapping("/import")
    public Result imp(@RequestBody MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Student> list = reader.readAll(Student.class);
        studentService.saveBatch(list);
        return Result.success();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Student> list = studentService.list();
        InExport.export(response, list, "学生信息");
    }
}
