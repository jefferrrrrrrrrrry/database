package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.output.CourseInfo;
import com.example.buaadb.mapper.CourseMapper;
import com.example.buaadb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
    public Result find(@RequestParam Integer cno, @RequestParam String cname, @RequestParam String tname
            , @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<CourseInfo> list = courseMapper.find(cno, cname, tname);
        int total = list.size();
        int totalPage = (total / pageSize) + ((total % pageSize > 0) ? 1 : 0);
        if (pageNum > totalPage || pageNum <= 0) {
            return Result.error("页码不合法");
        }
        List<CourseInfo> page = list.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, total));
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("totalPage", totalPage);
        res.put("page", page);
        return Result.success(res);
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

    @GetMapping("/page")
    public IPage<Course> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize) {
        IPage<Course> page = new Page<>(pageNum, pageSize);
        return courseService.page(page);
    }
}
