package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String from;
    private String dest;
    private String content;
    private Date date;
}
