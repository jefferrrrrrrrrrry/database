package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.*;
import com.example.buaadb.entity.Class;
import com.example.buaadb.mapper.ManagerMapper;
import com.example.buaadb.service.ManagerService;
import com.example.buaadb.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public Result find(@RequestParam int tno, @RequestParam String tname) {
        return Result.success(managerMapper.find(tno, tname));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Manager manager) {
        if (userService.getById(manager.getMno()) != null) {
            return Result.error(Status.ERROR, "添加失败，用户名已存在");
        } else {
            userService.save(new User(manager.getMno(), manager.getMpassword(), 3));
            return Result.success(managerService.save(manager));
        }
    }

    @DeleteMapping("/{tno}")
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
