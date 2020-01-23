package com.itsu.spbmanagevue;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itsu.spbmanagevue.dao.UserDAO;
import com.itsu.spbmanagevue.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbManageVueApplicationTests {
    @Resource
    private UserDAO userDAO;

    @Test
    public void test1() {
        User user = userDAO.getUserInfoById(1);
        System.out.println(JSON.toJSONString(user));
    }


    @Test
    public void test2() {
        QueryWrapper<User> condition = new QueryWrapper<>();
        condition.eq("username", "suben");
        condition.eq("pwd", "suben");
        User user = userDAO.selectOne(condition);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void test3() {
        Page<User> page = new Page<>(1, 2);
        page = userDAO.selectPage(page, null);
        System.out.println(page.getTotal());
        System.out.println(page.getCurrent());
        System.out.println(JSON.toJSONString(page.getRecords()));
        System.out.println(page.getSize());
        System.out.println(page.getPages());
    }
}
