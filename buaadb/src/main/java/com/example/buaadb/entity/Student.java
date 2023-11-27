package com.example.buaadb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Student {
    private int sno;
    private String sname;
    private int sage;
    private String ssex;
    private int sgrade;
    @JsonIgnore
    private int spassword;
    private int clno;
}
