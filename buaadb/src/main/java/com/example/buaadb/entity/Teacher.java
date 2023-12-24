package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    @TableId
    private String tno;
    private String tname;
    private String tsex;
    private String ttitle;
    private Integer scno;
    private String tpassword;
}
