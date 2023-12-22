package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Teacher implements UserEntity {
    @TableId
    private String tno;
    private String tname;
    private String ttitle;
    private String tpassword;
}
