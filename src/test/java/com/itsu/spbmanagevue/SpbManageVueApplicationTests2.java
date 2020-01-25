package com.itsu.spbmanagevue;

import com.alibaba.fastjson.JSON;
import com.itsu.spbmanagevue.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author suben
 * @create time 2020/1/25 11:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpbManageVueApplicationTests2 {

    @Resource
    RoleService roleService;

    @Test
    public void test1() throws Exception {
        List<Map> roleNames = roleService.getAllRoleNames();
        System.out.println(JSON.toJSONString(roleNames));
    }
}
