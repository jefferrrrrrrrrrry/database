package com.example.buaadb.entity.output;

import com.baomidou.mybatisplus.annotation.TableId;

public class StudentInfo {
    @TableId
    private String sno;
    private String sname;
    private Integer sage;
    private String ssex;
    private Integer sgrade;
    private String spassword;
    private Integer clno;
}
