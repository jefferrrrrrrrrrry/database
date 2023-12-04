package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Class {
    @TableId
    private int clno;
    private int scno;
}
