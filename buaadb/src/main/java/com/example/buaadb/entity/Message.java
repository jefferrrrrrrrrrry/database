package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    @TableId
    private Integer id;
    private String from;
    private String dest;
    private String content;
    private Date date;

    public Message(Integer id, String sender, String receiver, String content) {
        this.id = id;
        from = sender;
        dest = receiver;
        this.content = content;
    }
}
