package com.example.buaadb.entity;

import lombok.Data;

@Data
public class Course {
    private int cno;
    private String cname;
    private String cpos;
    private int tno;
    private String ctype;
    private int ccredit;
    private int ccapacity;
    private int cremain;
}
