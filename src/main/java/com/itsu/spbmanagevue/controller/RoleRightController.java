package com.itsu.spbmanagevue.controller;

import com.itsu.spbmanagevue.components.annotations.RefreshUserToken;
import com.itsu.spbmanagevue.components.annotations.UserLoginToken;
import com.itsu.spbmanagevue.response.ResponseObj;
import com.itsu.spbmanagevue.service.RoleRightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author suben
 * @create time 2020/1/25 16:48
 */
@RestController
@Slf4j
@RequestMapping("/apis")
public class RoleRightController {

    @Resource
    private RoleRightService roleRightService;


    @GetMapping("/rightlist")
    @UserLoginToken
    @RefreshUserToken
    public ResponseObj getRightlist() throws Exception {
        List<HashMap> roleRightList = roleRightService.getRoleRightList();
        return ResponseObj.createSuccess(roleRightList);
    }
}
