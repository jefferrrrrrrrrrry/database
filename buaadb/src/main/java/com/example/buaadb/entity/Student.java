package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.buaadb.entity.output.CourseInfo;
import lombok.Data;

import java.util.List;

@Data
public class Student {
    @TableId
    private String sno;
    private String sname;
    private Integer sage;
    private String ssex;
    private Integer sgrade;
    private Integer scredit;
    private String spassword;
    private Integer clno;
}
