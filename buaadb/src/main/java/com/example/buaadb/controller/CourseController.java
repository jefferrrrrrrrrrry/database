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
import java.util.Objects;

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

    @GetMapping("/findApprove")
    public Result findApprove(@RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        return Result.success(PageDivision.getPage(courseMapper.findApprove(TokenUtils.getUsername(), cno, cname, ""), pageNum, pageSize));
    }

    @GetMapping("/managerfindPend")
    public Result managerfindPend(@RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        return Result.success(PageDivision.getPage(courseMapper.managerfindPend(cno, cname, ""), pageNum, pageSize));
    }

    @GetMapping("/findPend")
    public Result findPend(@RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        return Result.success(PageDivision.getPage(courseMapper.findPend(TokenUtils.getUsername(), cno, cname, ""), pageNum, pageSize));
    }

    @PostMapping("/selectCourse")
    public Result selectCourse(@RequestBody String cno) { // 学生选课
        QueryWrapper<Sel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sno", TokenUtils.getUsername());
        queryWrapper.eq("cno", cno);
        Sel sel = selService.getOne(queryWrapper);
        if (sel != null) {
            throw new ServiceException(Status.ERROR, "已选此课");
        }
        Course course = courseService.getById(cno);
        if (course == null) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        if (course.getCremain() <= 0) {
            throw new ServiceException(Status.ERROR, "此课已满");
        }
        selService.save(new Sel(cno, TokenUtils.getUsername(), null));
        course.setCremain(course.getCremain() - 1);
        courseService.updateById(course);
        return Result.success();
    }

    @PostMapping("/withdraw")
    public Result withdraw(@RequestBody String cno) {
        QueryWrapper<Sel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sno", TokenUtils.getUsername());
        queryWrapper.eq("cno", cno);
        Sel sel = selService.getOne(queryWrapper);
        if (sel.getSegrade() != null) {
            throw new ServiceException(Status.ERROR, "教师已打分，无法退课");
        }
        try {
            selService.remove(queryWrapper);
            Course course = courseService.getById(cno);
            course.setCremain(course.getCremain() + 1);
            courseService.updateById(course);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) { // 教师提交加课申请
        course.setTno(TokenUtils.getUsername());
        course.setStatus(0);
        course.setCremain(course.getCcapacity());
        try {
            courseService.save(course);
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        return Result.success();
    }

    @PostMapping("/manageradd")
    public Result manageradd(@RequestBody Course course) { // 管理员提交加课申请
        course.setStatus(1);
        course.setCremain(course.getCcapacity());
        try {
            courseService.save(course);
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        return Result.success();
    }

    @PostMapping("/approve")
    public Result approve(@RequestBody String cno){ // 管理员同意加课
        try {
            courseMapper.approve(cno);
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        return Result.success();
    }

    @PostMapping("/disapprove")
    public Result disapprove(@RequestBody String cno){ // 管理员拒绝加课
        try {
            courseService.removeById(cno);
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        return Result.success();
    }

    @GetMapping("/studentselect")
    public Result studentselect( // 学生查询已选课程
            @RequestParam(defaultValue = "") String cno, @RequestParam(defaultValue = "") String cname, @RequestParam(defaultValue = "") String tname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        return Result.success(PageDivision.getPage(courseMapper.studentselect(TokenUtils.getUsername(), cno, cname, tname), pageNum, pageSize));
    }

    @DeleteMapping("/{cno}")
    public Result del(@PathVariable String cno) { // 删除课程
        try {
            courseService.removeById(cno);
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        return Result.success();
    }

    @PostMapping("/recordgrade")
    public Result recordgrade(@RequestBody Sel sel) {
        Course course = courseService.getById(sel.getCno());
        if (course == null) {
            throw new ServiceException(Status.ERROR, "课程不存在");
        }
        if (!Objects.equals(course.getTno(), TokenUtils.getUsername())) {
            throw new ServiceException(Status.ERROR, "你没有权限更改此成绩");
        }
        try {
            System.out.println(sel);
            QueryWrapper<Sel> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cno", sel.getCno());
            queryWrapper.eq("sno", sel.getSno());
            selService.update(sel, queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Course course) { // 更新课程
        try {
            return Result.success(courseService.updateById(course));
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }

    @GetMapping("/average")
    public Result average(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) { // 计算课程平均分
        return Result.success(PageDivision.getPage(courseMapper.average(TokenUtils.getUsername()), pageNum, pageSize));
    }

    @GetMapping("/manageraverage")
    public Result manageraverage(@RequestParam String cno, @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "20") Integer pageSize) { // 计算课程平均分
        return Result.success(PageDivision.getPage(courseMapper.manageraverage(), pageNum, pageSize));
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
