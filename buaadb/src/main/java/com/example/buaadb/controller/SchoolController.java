package com.example.buaadb.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.School;
import com.example.buaadb.function.InExport;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.mapper.SchoolMapper;
import com.example.buaadb.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/add")
    public Result add(@RequestBody School school) {
        return Result.success(schoolService.save(school));
    }


    @PostMapping("/update")
    public Result update(@RequestBody School school) {
        return Result.success(schoolService.updateById(school));
    }

    @DeleteMapping("/{scno}")
    public Result del(@PathVariable String scno) {
        return Result.success(schoolService.removeById(scno));
    }

    @GetMapping("/find")
    public Result find(@RequestParam String scname // 根据系名模糊查找系
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("scname", scname);
        return Result.success(PageDivision.getPage(schoolService.list(queryWrapper), pageNum, pageSize));
    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<School> list = reader.readAll(School.class);
        schoolService.saveBatch(list);
        return Result.success();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<School> list = schoolService.list();
        InExport.export(response, list, "院系信息");
    }
}
