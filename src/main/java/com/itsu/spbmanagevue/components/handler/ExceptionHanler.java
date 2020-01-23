package com.itsu.spbmanagevue.components.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.itsu.spbmanagevue.components.exception.AuthenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 苏犇
 * @create time 2020/1/21 21:53
 */
@ControllerAdvice
@Slf4j
public class ExceptionHanler {

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseBody
    public Map<String, Object> handleAuthenException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage(), e);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        if (e instanceof TokenExpiredException) {
            map.put("msg", "登录超时");
        } else if (e instanceof AuthenException) {
            map.put("msg", e.getMessage());
        } else {

            map.put("msg", "token验证失败");
        }
        return map;
    }
}
