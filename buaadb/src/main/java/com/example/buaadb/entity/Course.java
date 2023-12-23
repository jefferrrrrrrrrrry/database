package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @TableId
    private String cno;
    private String cname;
    private String cpos;
    private String tno;
    private String ctype;
    private Integer ccredit;
    private Integer ccapacity;
    private Integer cremain;
    private Integer status;
}
