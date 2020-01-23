package com.itsu.spbmanagevue.controller;

import com.itsu.spbmanagevue.components.annotations.UserLoginToken;
import com.itsu.spbmanagevue.components.constant.ProjectConstant;
import com.itsu.spbmanagevue.entity.User;
import com.itsu.spbmanagevue.response.ResonseObj;
import com.itsu.spbmanagevue.service.TokenService;
import com.itsu.spbmanagevue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 苏犇
 * @create time 2020/1/21 21:21
 */
@RestController
@RequestMapping("/apis")
@Slf4j
public class UserController {

    @Resource
    TokenService tokenService;

    @Resource
    UserService userService;

    @PostMapping("/login")
    public ResonseObj login(@RequestBody User user) throws Exception {
        ResonseObj result = userService.login(user);
        Integer code = result.getCode();
        if (code != 0) {
            return result;
        }
        String token = "";
        if (user.isRem()) {
            token = tokenService.generateToken(user, ProjectConstant.tokenMaxTime);
        } else {
            token = tokenService.generateToken(user, 1000 * 60 * 30);
        }
        HashMap<Object, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("username", user.getUsername());
        result.setData(data);
        return result;
    }

    @GetMapping("/chk")
    @UserLoginToken
    public String check() {
        return "success";
    }
}