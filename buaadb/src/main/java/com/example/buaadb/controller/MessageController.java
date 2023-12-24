package com.example.buaadb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.entity.Message;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.function.PageDivision;
import com.example.buaadb.function.TokenUtils;
import com.example.buaadb.mapper.MessageMapper;
import com.example.buaadb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.util.Date;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageMapper mapper;
    @Autowired
    private MessageService service;


    @PostMapping("/send")
    public Result send(@RequestBody Message message) {
        message.setFrom(TokenUtils.getUsername());
        message.setDate(new Date());
        try {
            service.save(message);
        } catch (Exception e){
            throw new ServiceException(Status.ERROR, "");
        }
        return Result.success();
    }

    @GetMapping("/get")
    public Result get(@RequestParam String user
            , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dest", user);
        queryWrapper.eq("read", 0);
        return Result.success(PageDivision.getPage(service.list(queryWrapper), pageNum, pageSize));
    }

    @PostMapping("/read")
    public Result read(@RequestBody Integer id) {
        try {
            Message message = new Message();
            message.setId(id);
            message.setRead(1);
            return Result.success(service.updateById(message));
        } catch (Exception e) {
            throw new ServiceException(Status.ERROR, "操作失败");
        }
    }
}
