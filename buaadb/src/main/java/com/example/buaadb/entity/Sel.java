package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sel {
    @TableId
    private String cno;
    @TableField
    private String sno;
    private Integer segrade;
}
