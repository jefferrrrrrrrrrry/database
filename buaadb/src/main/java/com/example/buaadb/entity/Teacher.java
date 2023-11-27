package com.example.buaadb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Teacher {
    private int tno;
    private String tname;
    private String ttitle;
    @JsonIgnore
    private String tpassword;
}
