package com.example.buaadb.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
