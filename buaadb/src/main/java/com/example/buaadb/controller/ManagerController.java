package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Class;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Manager;
import com.example.buaadb.mapper.ManagerMapper;
import com.example.buaadb.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ManagerService managerService;

    @GetMapping("/find")
    public Result find(@RequestParam int tno, @RequestParam String tname) {
        return Result.success(managerMapper.find(tno, tname));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Manager manager) {
        return Result.success(managerService.save(manager));
    }

    @DeleteMapping("/{mno}")
    public Result del(@PathVariable int mno) {
        managerService.removeById(mno);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Manager manager) {
        return Result.success(managerService.updateById(manager));
    }

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Manager> list = managerService.list();
        managerService.export(response, list);
        return Result.success();
    }
}
