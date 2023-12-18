package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Sel {
    @TableId
    private Integer cno;
    @TableField
    private Integer sno;
    private Integer segrade;
}
