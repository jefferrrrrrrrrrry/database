package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.entity.School;
import com.example.buaadb.entity.Student;
import com.example.buaadb.mapper.SchoolMapper;
import com.example.buaadb.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<School> list = schoolService.list();
        schoolService.export(response, list);
        return Result.success();
    }
}
