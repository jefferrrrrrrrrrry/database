package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Manager;
import com.example.buaadb.mapper.ManagerMapper;
import com.example.buaadb.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ManagerService managerService;

    @PostMapping("/login")
    public int login(@RequestBody LogInfo logInfo) {
        return managerService.login(logInfo);
    }

    @PostMapping("/changepassword")
    public boolean changepassword() {
        return false; // TODO
    }

    @GetMapping("/find")
    public Result find(@RequestParam int tno, @RequestParam String tname) {
        return Result.success(managerMapper.find(tno, tname));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Manager manager) {
        return Result.success(managerService.save(manager));
    }
}
