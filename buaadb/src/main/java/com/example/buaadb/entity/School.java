package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class School {
    @TableId
    private Integer scno;
    private String scname;
}
