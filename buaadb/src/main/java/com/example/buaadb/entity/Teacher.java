package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Teacher {
    @TableId
    private Integer tno;
    private String tname;
    private String ttitle;
    @JsonIgnore
    private String tpassword;
}
