package com.example.buaadb.controller.logInfo;

import lombok.Data;

@Data
public class LogInfo {
    private Integer username; // 该类用于接受前端的登录信息
    private String password;
}
