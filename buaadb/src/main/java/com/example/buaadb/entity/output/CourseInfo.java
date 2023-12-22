package com.example.buaadb.entity.output;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CourseInfo {
    @TableId
    private String cno;
    private String cname;
    private String cpos;
    private Integer tno;
    private String tname;
    private String ctype;
    private Integer ccredit;
    private Integer ccapacity;
    private Integer cremain;
    private Integer score;
}
