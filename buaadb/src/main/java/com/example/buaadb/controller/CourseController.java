package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.output.CourseInfo;
import com.example.buaadb.function.InExport;
import com.example.buaadb.function.PageDivision;
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
    public Result find(@RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<CourseInfo> list = courseMapper.find(cno, cname, tname);
        return Result.success(PageDivision.getPage(list, pageNum, pageSize));
    }

    @PostMapping("/teacherfind")
    public Result teacherfind(@RequestBody Teacher teacher) {
        return Result.success(courseMapper.teacherfind(teacher.getTno()));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        course.setStatus(0);
        return Result.success(courseService.save(course));
    }

    @PostMapping("/approve")
    public Result approve(@RequestBody Course course){
        course.setStatus(1);
        courseMapper.approve(course.getCno());
        return Result.success();
    }

    @PostMapping("/disapprove")
    public Result disapprove(@RequestBody Course course){
        courseService.removeById(course.getCno());
        return Result.success();
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
        InExport.export(response, list, "课程信息");
        return Result.success();
    }

}
