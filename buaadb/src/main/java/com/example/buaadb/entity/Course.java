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
    @Alias("课程编号")
    private String cno;
    @Alias("课程名")
    private String cname;
    @Alias("开课地点")
    private String cpos;
    @Alias("教师编号")
    private String tno;
    @Alias("课程类型")
    private String ctype;
    private Integer ccredit;
    private Integer ccapacity;
    private Integer cremain;
    private Integer status;
}
