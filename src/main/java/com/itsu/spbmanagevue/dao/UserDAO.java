package com.itsu.spbmanagevue.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itsu.spbmanagevue.entity.User;

/**
 * @author 苏犇
 * @create time 2020/1/20 23:07
 */
public interface UserDAO extends BaseMapper<User> {

    User getUserInfoById(Integer uid);
}
