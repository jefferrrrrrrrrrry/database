package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Manager {
    @TableId
    private String mno;
    private String mname;
    private String mpassword;
}
