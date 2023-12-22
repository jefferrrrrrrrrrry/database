package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Teacher {
    @TableId
    private String tno;
    private String tname;
    private String ttitle;
    private String tpassword;
}
