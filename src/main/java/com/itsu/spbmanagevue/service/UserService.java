package com.itsu.spbmanagevue.service;

import com.itsu.spbmanagevue.entity.User;
import com.itsu.spbmanagevue.response.ResonseObj;

/**
 * @author 苏犇
 * @create time 2020/1/21 20:55
 */
public interface UserService {
    default User getUserByUserName(String username) {
        return null;
    }


    default User getUserById(Integer id) {
        return null;
    }


   ResonseObj login(User user) throws Exception;
}
