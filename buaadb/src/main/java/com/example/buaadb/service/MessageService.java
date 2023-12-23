package com.example.buaadb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.entity.Message;
import com.example.buaadb.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {
    @Autowired
    private MessageMapper mapper;
}
