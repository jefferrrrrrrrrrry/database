package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.mapper.EchartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private EchartsMapper mapper;
    @GetMapping("/getratecno")
    public Result getRateCno(@RequestParam String cno) {
        try {
            return Result.success(mapper.getRateCno(cno));
        } catch (Exception e){
            throw new ServiceException(Status.ERROR, "数据访问失败");
        }
    }

    @GetMapping("/getratesno")
    public Result getRateSno(@RequestParam String Sno) {
        try {
            return Result.success(mapper.getRateSno(Sno));
        } catch (Exception e){
            throw new ServiceException(Status.ERROR, "数据访问失败");
        }
    }
}
