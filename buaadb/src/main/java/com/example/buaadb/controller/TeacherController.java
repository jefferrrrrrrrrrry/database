package com.example.buaadb.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.InExport;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.TeacherMapper;
import com.example.buaadb.service.TeacherService;
import com.example.buaadb.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public Result find(@RequestParam(defaultValue = "") String tno, @RequestParam(defaultValue = "") String tname, @RequestParam(defaultValue = "") String scname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        return Result.success(PageDivision.getPage(teacherMapper.find(tno, tname, scname), pageNum, pageSize));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        if (userService.getById(teacher.getTno().replace("\"", "")) != null) {
            throw new ServiceException(Status.ERROR, "添加失败，用户名已存在");
        } else {
            // userService.save(new User(teacher.getTno(), teacher.getTpassword(), 2));
            try {
                return Result.success(teacherService.save(teacher));
            } catch (Exception e) {
                throw new ServiceException(Status.ERROR, "操作失败");
            }
        }
    }

    @DeleteMapping("/{tno}")
    public Result del(@PathVariable String tno) {
        try {
            teacherService.removeById(tno.replace("\"", ""));
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Teacher teacher) {
        try {
            if (StrUtil.isBlank(teacher.getTpassword())) {
                teacher.setTpassword(null);
            }
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        boolean b = teacherService.updateById(teacher);
        if (!b) {
            throw new ServiceException(Status.ERROR, "更新失败，学工号不存在");
        }
        return Result.success();
    }

    @GetMapping("/profile")
    public Result profile() {
        try {
            return Result.success(teacherMapper.profile(TokenUtils.getUsername()).get(0));
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
            List<Teacher> list = reader.readAll(Teacher.class);
            teacherService.saveBatch(list);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "导入失败");
        }

    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Teacher> list = teacherService.list();
        InExport.export(response, list, "教师信息");
    }
}
