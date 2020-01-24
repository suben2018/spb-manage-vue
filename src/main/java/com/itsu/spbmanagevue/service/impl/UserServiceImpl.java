package com.itsu.spbmanagevue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itsu.spbmanagevue.components.exception.SystemException;
import com.itsu.spbmanagevue.dao.UserDAO;
import com.itsu.spbmanagevue.entity.User;
import com.itsu.spbmanagevue.response.ResonseObj;
import com.itsu.spbmanagevue.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 苏犇
 * @create time 2020/1/21 21:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> condition = new QueryWrapper<>();
        condition.eq("username", username);
        return userDAO.selectOne(condition);
    }

    @Override
    public User getUserById(Integer id) {
        return userDAO.selectById(id);
    }

    @Override
    public ResonseObj login(User user) throws Exception {
        if (user == null) {
            throw new SystemException("输入参数为null");
        }
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> condition = new QueryWrapper<>();
        condition.eq("username", user.getUsername());
        condition.eq("pwd", user.getPwd());
        ResonseObj resonseObj = null;
        user = userDAO.selectOne(condition);
        if (user == null) {
            resonseObj = ResonseObj.createError(100, "用户名或密码错误");
        } else if (!"Y".equals(user.getStat())) {
            resonseObj = resonseObj.createError(101, "用户已被锁定");
        } else {
            resonseObj = resonseObj.createSuccess();
        }
        return resonseObj;
    }

    @Override
    public IPage<HashMap> getUsersByPage(Integer currentPage, Integer pageSize) {
        Page page = new Page<>();
        page.setCurrent(currentPage.longValue());
        page.setSize(pageSize.longValue());
        return userDAO.selectUserByPage(page);
    }

}
