package com.example.buaadb.controller;

import com.example.buaadb.common.Result;
import com.example.buaadb.common.Status;
import com.example.buaadb.entity.Message;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.mapper.MessageMapper;
import com.example.buaadb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageMapper mapper;
    @Autowired
    private MessageService service;


    @PostMapping("/send")
    public Result send(@RequestBody String receiver, @RequestBody String content) {
        if(!service.save(new Message(0, "sender", receiver, content))) {
            throw new ServiceException(Status.ERROR, "发送失败");
        } else {
            return Result.success();
        }
    }
}
