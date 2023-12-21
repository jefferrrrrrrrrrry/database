package com.example.buaadb.function;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {
    public static String genToken(String userId, String sign) {
        String token = JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // token两小时过期
                .sign(Algorithm.HMAC256(sign));
        return token;
    }
}
