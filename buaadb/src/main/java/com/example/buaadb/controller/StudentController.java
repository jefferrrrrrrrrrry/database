package com.example.buaadb.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Student;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.InExport;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.function.TokenUtils;
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
    @GetMapping("/find") // 找学生
    public Result findStudent(@RequestParam(defaultValue = "") String sno,
                              @RequestParam(defaultValue = "") String sname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        return Result.success(PageDivision.getPage(studentMapper.findStudent(sno, sname), pageNum, pageSize));
    }

    @GetMapping("/coursechosen")
    public Result coursechosen(@RequestParam String cno // 选某课的名单
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) { // 查询某一门课被什么学生选
        return Result.success(PageDivision.getPage(studentMapper.coursechosen(cno), pageNum, pageSize));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        if (userService.getById(student.getSno().replace("\"", "")) != null) {
            return Result.error(Status.ERROR, "添加失败，用户名已存在");
        } else {
            student.setScredit(0);
            try {
                return Result.success(studentService.save(student));
            } catch (Exception e) {
                throw new ServiceException(Status.ERROR, "操作失败");
            }
        }
    }

    @DeleteMapping("/{sno}")
    public Result del(@PathVariable String sno) {
        try {
            studentService.removeById(sno.replace("\"", ""));
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }
    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        try {
            if (StrUtil.isBlank(student.getSpassword())) {
                student.setSpassword(null);
            }
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        boolean b = studentService.updateById(student);
        if (!b) {
            throw new ServiceException(Status.ERROR, "更新失败，学工号不存在");
        }
        return Result.success();
    }

    @GetMapping("/profile")
    public Result profile() {
        try {
            return Result.success(studentMapper.profile(TokenUtils.getUsername()).get(0));
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/import")
    public Result imp(@RequestBody MultipartFile file) throws IOException {
        try {
            if (TokenUtils.getCurrentUser().getPermission() < 3) {
                throw new ServiceException(Status.ERROR, "无权限");
            }
            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            List<Student> list = reader.readAll(Student.class);
            studentService.saveBatch(list);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "导入失败");
        }
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Student> list = studentService.list();
        InExport.export(response, list, "学生信息");
    }
}
