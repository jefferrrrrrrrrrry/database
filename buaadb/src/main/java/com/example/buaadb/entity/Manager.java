package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Manager implements UserEntity {
    @TableId
    private String mno;
    private String mname;
    @JsonIgnore
    private String mpassword;
}
