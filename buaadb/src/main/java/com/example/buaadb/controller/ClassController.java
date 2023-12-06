package com.example.buaadb.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Class;
import com.example.buaadb.mapper.ClassMapper;
import com.example.buaadb.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private ClassService classService;

    @GetMapping("/")
    public Result findAll() {
        return Result.success(classService.list());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Class class1) {
        return Result.success(classService.save(class1));
    }

    @DeleteMapping("/{clno}")
    public Result del(@PathVariable int clno) {
        classService.removeById(clno);
        return Result.success();
    }

    @PostMapping("/import")
    public Result imp(@RequestBody MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Class> list = reader.readAll(Class.class);
        classService.saveBatch(list);
        return Result.success();
    }

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Class> list = classService.list();
        classService.export(response, list);
        return Result.success();
    }

}
