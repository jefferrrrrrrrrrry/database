package com.example.buaadb.controller;

import com.example.buaadb.entity.School;
import com.example.buaadb.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolMapper schoolMapper;

}
