package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Student;
import com.example.buaadb.function.InExport;
import com.example.buaadb.mapper.StudentMapper;
import com.example.buaadb.service.StudentService;
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

    @PostMapping("/login")
    public Result login(@RequestBody LogInfo logInfo) {
        return Result.success(studentService.login(logInfo));
    }

    @PostMapping("/changepassword")
    public boolean changepassword() {
        return false; // TODO
    }

    @GetMapping("/find")
    public Result find(@RequestParam String sno, @RequestParam String sname) {
        return Result.success(studentMapper.find(sno, sname));
    }

    @PostMapping("/test")
    public Result test(@RequestBody int num) {
        return Result.success("POST内容：" + num);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        return Result.success(studentService.save(student));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        return Result.success(studentService.updateById(student));
    }

    @DeleteMapping("/{sno}")
    public Result del(@PathVariable String sno) {
        studentService.removeById(sno);
        return Result.success();
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
    public Result export(HttpServletResponse response) throws IOException {
        List<Student> list = studentService.list();
        InExport.export(response, list, "学生信息");
        return Result.success();
    }
}
