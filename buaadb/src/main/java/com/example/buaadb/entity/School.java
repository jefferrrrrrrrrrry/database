package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class School {
    @TableId
    private int scno;
    private String scname;
}
