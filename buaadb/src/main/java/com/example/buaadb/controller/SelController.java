package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Sel;
import com.example.buaadb.entity.Student;
import com.example.buaadb.mapper.SelMapper;
import com.example.buaadb.service.SelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sel")
public class SelController {
    @Autowired
    private SelMapper selMapper;
    @Autowired
    private SelService selService;

    @PostMapping("/add")
    public Result add(@RequestBody Sel sel) {
        return Result.success(selService.save(sel));
    }

    @PostMapping("/setScore")
    public Result setScore(@RequestParam String cno, @RequestParam String sno, @RequestParam Integer segrade){
        selMapper.setScore(cno, sno, segrade);
        return Result.success();
    }

    @GetMapping("/getScore")
    public Result getScore(@RequestParam String cno, @RequestParam String sno){
        return Result.success(selMapper.getScore(cno, sno));
    }
}
