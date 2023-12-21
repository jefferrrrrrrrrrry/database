package com.example.buaadb.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.buaadb.common.Status;
import com.example.buaadb.entity.User;
import com.example.buaadb.exception.ServiceException;
import com.example.buaadb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Status.UNKNOWN_ERROR, "未获取到token信息");
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Status.UNKNOWN_ERROR, "token验证失败");
        }
        User user = userService.getById(userId);
        if (user == null) {
            throw new ServiceException(Status.UNKNOWN_ERROR, "用户不存在");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getSys_password())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Status.UNKNOWN_ERROR, "token验证失败，请重新登录");
        }
        return true;
    }
}
