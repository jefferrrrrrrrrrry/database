package com.example.buaadb.function;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.buaadb.common.Status;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.service.UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userid = JWT.decode(token).getAudience().get(0);
                return staticuserService.getById(userid);
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new ServiceException(Status.ERROR, "未获取到用户，请尝试重新登录");
        }
    }

    public static String getUsername() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                return JWT.decode(token).getAudience().get(0);
            }
        } catch (Exception e){
            throw new ServiceException(Status.ERROR, "未获取到用户，请尝试重新登录");
        }
        return null;
    }
}
