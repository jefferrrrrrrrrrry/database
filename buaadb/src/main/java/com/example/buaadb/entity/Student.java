package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId
    private String sno;
    private String sname;
    private Integer sage;
    private String ssex;
    private Integer sgrade;
    private String spassword;
    private Integer clno;
}
