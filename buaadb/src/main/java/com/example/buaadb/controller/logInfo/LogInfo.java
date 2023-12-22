package com.example.buaadb.controller.logInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LogInfo {
    private String sys_username;
    private String sys_password;
    private String token;
    private Integer permission;
}
