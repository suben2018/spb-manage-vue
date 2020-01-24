package com.itsu.spbmanagevue.dao;

import com.itsu.spbmanagevue.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author suben
 * @create time 2020/1/23 18:27
 */
public interface MenuDAO {
    List<Menu> getUserMenuByUserName(String username);

    List<Integer> getMenuButton(@Param("menuId") Integer menuId);
}
