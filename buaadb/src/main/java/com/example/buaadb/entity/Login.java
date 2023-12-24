package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;

@Data
public class Login {
    @TableId(type = IdType.AUTO)
    public Integer lno;
    public String username;
    public String password;
    public Integer success;
    public Date date;
}
