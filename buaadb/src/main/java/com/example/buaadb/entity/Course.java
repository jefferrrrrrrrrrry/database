package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Course {
    @TableId
    private String cno;
    private String cname;
    private String cpos;
    private Integer tno;
    private String ctype;
    private Integer ccredit;
    private Integer ccapacity;
    private Integer cremain;
}
