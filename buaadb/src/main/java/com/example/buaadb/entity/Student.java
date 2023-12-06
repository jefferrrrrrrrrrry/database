package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Student {
    @TableId
    private Integer sno;
    private String sname;
    private Integer sage;
    private String ssex;
    private Integer sgrade;
    @JsonIgnore
    private Integer spassword;
    private Integer clno;
}
