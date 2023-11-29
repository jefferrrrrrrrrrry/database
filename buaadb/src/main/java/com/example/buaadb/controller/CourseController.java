package com.example.buaadb.controller;


import com.example.buaadb.entity.Course;
import com.example.buaadb.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/")
    public List<Course> findAll() {
        return courseMapper.findAll();
    }
}
