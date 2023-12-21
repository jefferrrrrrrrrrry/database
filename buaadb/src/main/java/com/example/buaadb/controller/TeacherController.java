package com.example.buaadb.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.function.InExport;
import com.example.buaadb.mapper.TeacherMapper;
import com.example.buaadb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
    public Result find(@RequestParam String tno, @RequestParam String tname) {
        return Result.success(teacherMapper.find(tno, tname));
    }

    @GetMapping("/findApprove")
    public Result findApprove(@RequestParam String tno){
        return Result.success(teacherMapper.findApprove(tno));
    }

    @GetMapping("/findPend")
    public Result findPend(@RequestParam String tno){
        return Result.success(teacherMapper.findPend(tno));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        return Result.success(teacherService.save(teacher));
    }

    @DeleteMapping("/{tno}")
    public Result del(@PathVariable int tno) {
        teacherService.removeById(tno);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Teacher teacher) {
        return Result.success(teacherService.updateById(teacher));
    }

    @PostMapping("/import")
    public Result imp(@RequestBody MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Teacher> list = reader.readAll(Teacher.class);
        teacherService.saveBatch(list);
        return Result.success();
    }

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Teacher> list = teacherService.list();
        InExport.export(response, list, "教师信息");
        return Result.success();
    }
}
