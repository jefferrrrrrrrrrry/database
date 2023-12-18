package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.mapper.CourseMapper;
import com.example.buaadb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public Result findAll() {
        return Result.success(courseService.list());
    }

    @GetMapping("/find")
    public Result find(@RequestParam Integer cno, @RequestParam String cname, @RequestParam String tname) {
        return Result.success(courseMapper.find(cno, cname, tname));
    }

    @PostMapping("/teacherfind")
    public Result teacherfind(@RequestBody Teacher teacher) {
        return Result.success(courseMapper.teacherfind(teacher.getTno()));
    }
    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        return Result.success(courseService.save(course));
    }

    @DeleteMapping("/{cno}")
    public Result del(@PathVariable String cno) {
        courseService.removeById(cno);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Course course) {
        return Result.success(courseService.updateById(course));
    }

    @PostMapping("/import")
    public Result imp(@RequestBody MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Course> list = reader.readAll(Course.class);
        courseService.saveBatch(list);
        return Result.success();
    }

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Course> list = courseService.list();
        courseService.export(response, list);
        return Result.success();
    }
}
