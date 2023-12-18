package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Course;
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

    public Result count() {
        return Result.success(courseService.count());
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

    // 分页查询 - mybatis-plus 的方式
    @GetMapping("/page")
    public IPage<Course> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam String cname) {
        IPage<Course> page =new Page<>(pageNum,pageSize);
        return courseService.page(page);
    }
}
