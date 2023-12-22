package com.example.buaadb.function;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.buaadb.entity.User;
import com.example.buaadb.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

public class TokenUtils {
    private static UserService staticuserService;
    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticuserService = userService;
    }
    public static String genToken(String userId, String sign) {
        String token = JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // token两小时过期
                .sign(Algorithm.HMAC256(sign));
        return token;
    }

    public static User getCurrentUser() {
        // TODO P16 56min
        return null;
    }
}
