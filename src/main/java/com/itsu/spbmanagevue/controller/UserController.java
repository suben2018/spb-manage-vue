package com.itsu.spbmanagevue.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itsu.spbmanagevue.components.annotations.RefreshUserToken;
import com.itsu.spbmanagevue.components.annotations.UserLoginToken;
import com.itsu.spbmanagevue.components.constant.ProjectConstant;
import com.itsu.spbmanagevue.components.exception.SystemException;
import com.itsu.spbmanagevue.entity.Menu;
import com.itsu.spbmanagevue.entity.User;
import com.itsu.spbmanagevue.response.ResonseObj;
import com.itsu.spbmanagevue.service.MenuService;
import com.itsu.spbmanagevue.service.TokenService;
import com.itsu.spbmanagevue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 苏犇
 * @create time 2020/1/21 21:21
 */
@RestController
@RequestMapping("/apis")
@Slf4j
public class UserController {

    @Resource
    MenuService menuService;

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

    @GetMapping("/menu/{username}")
    @UserLoginToken
    @RefreshUserToken
    public ResonseObj getUserMenusTree(@PathVariable("username") String username) throws SystemException {
        List<Menu> menus = menuService.getMenusForCurrentUser(username);
        ResonseObj resonseObj = ResonseObj.createSuccess(menus);
        return resonseObj;
    }

    @GetMapping("/userlist/{currentPage}/{pageSize}")
    @UserLoginToken
    @RefreshUserToken
    public ResonseObj getUserListData(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
//        Assert.notEmpty(new Object[]{currentPage, pageSize}, "分页参数为空");
        IPage<HashMap> page = userService.getUsersByPage(currentPage, pageSize);
        Map data = new HashMap();
        data.put("total", page.getTotal());
        data.put("users", page.getRecords());
        return ResonseObj.createSuccess(data);
    }

    @GetMapping("/usermenubutton/{menuId}")
    @UserLoginToken
    @RefreshUserToken
    public ResonseObj getUserMenuButton(@PathVariable("menuId") Integer menuId) {
        List<Integer> buttonIds = menuService.getMenuButtonId(menuId);
        return ResonseObj.createSuccess(buttonIds);

    }
}
