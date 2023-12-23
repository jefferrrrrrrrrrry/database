package com.example.buaadb.entity.output;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StudentInfo {
    @TableId
    private String sno;
    private String sname;
    private Integer sage;
    private String ssex;
    private Integer sgrade;
    private String spassword;
    private Integer clno;
    private Integer scno;
    private String scname;
}
