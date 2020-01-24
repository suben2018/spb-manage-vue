package com.itsu.spbmanagevue.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itsu.spbmanagevue.entity.User;

import java.util.HashMap;

/**
 * @author 苏犇
 * @create time 2020/1/20 23:07
 */
public interface UserDAO extends BaseMapper<User> {

    User getUserInfoById(Integer uid);

    IPage<HashMap> selectUserByPage(Page page);

}
