package com.example.buaadb.controller;

import com.example.buaadb.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SchoolController {
    @Autowired
    private SchoolMapper schoolMapper;
}
