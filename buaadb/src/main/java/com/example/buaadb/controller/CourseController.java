package com.example.buaadb.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Sel;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.output.CourseInfo;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.InExport;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.CourseMapper;
import com.example.buaadb.service.CourseService;
import com.example.buaadb.service.SelService;
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
    @Autowired
    private SelService selService;

    @GetMapping("/")
    public Result findAll() {
        return Result.success(courseService.list());
    }

    @GetMapping("/find")
    public Result find(@RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) { // 查询总课表
        List<CourseInfo> list = courseMapper.find(cno, cname, tname);
        return Result.success(PageDivision.getPage(list, pageNum, pageSize));
    }

    @GetMapping("/teacherfind")
    public Result teacherfind(@RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        return Result.success(PageDivision.getPage(courseMapper.teacherfind(TokenUtils.getUsername(), cno, cname, ""),
                pageNum, pageSize));
    }

    @PostMapping("/selectCourse")
    public Result selectCourse(@RequestBody String cno) { // 学生选课
        boolean b = selService.save(new Sel(cno, TokenUtils.getUsername(), null));
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/withdraw")
    public Result withdraw(@RequestBody String cno) {
        QueryWrapper<Sel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cno", cno);
        boolean b = selService.remove(queryWrapper);
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) { // 教师提交加课申请
        course.setTno(TokenUtils.getUsername());
        course.setStatus(0);
        boolean b = courseService.save(course);
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/manageradd")
    public Result manageradd(@RequestBody Course course) { // 管理员提交加课申请
        course.setStatus(1);
        boolean b = courseService.save(course);
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/approve")
    public Result approve(@RequestBody String cno){ // 管理员同意加课
        int i = courseMapper.approve(cno);
        if (i > 1) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/disapprove")
    public Result disapprove(@RequestBody String cno){ // 管理员拒绝加课
        boolean b = courseService.removeById(cno);
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @GetMapping("/studentselect")
    public Result studentselect(@RequestParam String sno // 学生查询已选课程
            , @RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        return Result.success(PageDivision.getPage(courseMapper.studentselect(sno, cno, cname, tname), pageNum, pageSize));
    }

    @DeleteMapping("/{cno}")
    public Result del(@PathVariable String cno) { // 删除课程
        boolean b = courseService.removeById(cno);
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("recordgrade")
    public Result recordgrade(@RequestBody Sel sel) {
        QueryWrapper<Sel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cno", sel.getCno());
        queryWrapper.eq("sno", sel.getSno());
        boolean b = selService.update(sel, queryWrapper);
        if (b) {
            return Result.success();
        } else {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Course course) { // 更新课程
        return Result.success(courseService.updateById(course));
    }

    @GetMapping("/average")
    public Result average(@RequestParam String cno) { // 计算课程平均分
        // return Result.success(courseMapper.average(cno));
        // TODO
        return Result.success();
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
    public void export(HttpServletResponse response) throws IOException {
        List<Course> list = courseService.list();
        InExport.export(response, list, "课程信息");
    }
}
