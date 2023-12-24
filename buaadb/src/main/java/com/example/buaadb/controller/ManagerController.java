package com.example.buaadb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.*;
import com.example.buaadb.entity.Class;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.function.TokenUtils;
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
    public Result find(@RequestParam(defaultValue = "") String mno, @RequestParam(defaultValue = "") String mname
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("mno", mno);
        queryWrapper.like("mname", mname);
        return Result.success(PageDivision.getPage(managerService.list(queryWrapper), pageNum, pageSize));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Manager manager) {
        if (userService.getById(manager.getMno()) != null) {
            return Result.error(Status.ERROR, "添加失败，用户名已存在");
        } else {
            // userService.save(new User(manager.getMno(), manager.getMpassword(), 3));
            return Result.success(managerService.save(manager));
        }
    }

    @DeleteMapping("/{mno}")
    public Result del(@PathVariable String mno) {
        if (mno.equals(TokenUtils.getUsername())) {
            return Result.error(Status.ERROR, "不能删除当前用户！");
        }
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
