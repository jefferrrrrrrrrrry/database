package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@TableName("sys_user")
public class User {
    @TableId
    private String sys_username;
    private String sys_password;
    private Integer permission;
    public static void sortByName(List<User> list, int up) {
        list.sort(((o1, o2) -> {
            if (o1.sys_username.compareTo(o2.sys_username) > 0) {
                return up;
            } else if (o1.sys_username.compareTo(o2.sys_username) == 0) {
                return 0;
            } else {
                return -up;
            }
        }));
    }

    public static void sortByPermission(List<User> list, int up) {
        list.sort(((o1, o2) -> {
            if (o1.permission > o2.permission) {
                return up;
            } else if (o1.permission == o2.permission) {
                return 0;
            } else {
                return -up;
            }
        }));
    }
}
