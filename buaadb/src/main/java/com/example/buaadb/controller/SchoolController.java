package com.example.buaadb.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.School;
import com.example.buaadb.function.InExport;
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

    @PostMapping("/update")
    public Result update(@RequestBody School school) {
        return Result.success(schoolService.updateById(school));
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
    public Result export(HttpServletResponse response) throws IOException {
        List<School> list = schoolService.list();
        InExport.export(response, list, "院系信息");
        return Result.success();
    }
}
