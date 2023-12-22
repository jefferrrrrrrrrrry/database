package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.entity.Menu;
import com.example.buaadb.mapper.MenuMapper;
import com.example.buaadb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuMapper menuMapper;
    private MenuService menuService;

    @GetMapping("")
    public Result findAll(){
        // TODO P20 45min
        return Result.success(menuService.list());
    }
}
