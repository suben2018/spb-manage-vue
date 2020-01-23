package com.itsu.spbmanagevue.components.aop;

import com.auth0.jwt.JWT;
import com.itsu.spbmanagevue.entity.User;
import com.itsu.spbmanagevue.service.TokenService;
import com.itsu.spbmanagevue.service.UserService;
import com.itsu.spbmanagevue.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author suben
 * @create time 2020/1/22 21:59
 */
@Aspect
@Slf4j
public class HandleRefreshUserToken {

    @Resource
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.itsu.spbmanagevue.components.annotations.RefreshUserToken)")
    public void delRule() {
    }

    @After("delRule()&& @annotation(refreshUserToken)")
    public void refresh(JoinPoint joinPoint, com.itsu.spbmanagevue.components.annotations.RefreshUserToken refreshUserToken) {
        String oldToken = ServletUtil.getRequest().getHeader("token");
        String userName = JWT.decode(oldToken).getAudience().get(0);
        Date expiresAt = JWT.decode(oldToken).getExpiresAt();
        long time = expiresAt.getTime() - System.currentTimeMillis();
        if (time <= 5 * 60 * 1000) {
            log.info("当前用户token过期时间小于5分钟，将重新分配token");
            User user = userService.getUserByUserName(userName);
            tokenService.generateNewToken(user, oldToken, refreshUserToken.timeMiles());
        }

    }

}
