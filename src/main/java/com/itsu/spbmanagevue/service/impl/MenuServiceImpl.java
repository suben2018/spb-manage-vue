package com.itsu.spbmanagevue.service.impl;

import com.itsu.spbmanagevue.components.exception.SystemException;
import com.itsu.spbmanagevue.dao.MenuDAO;
import com.itsu.spbmanagevue.entity.Menu;
import com.itsu.spbmanagevue.service.MenuService;
import com.itsu.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author suben
 * @create time 2020/1/23 18:49
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDAO menuDAO;

    @Override
    public List<Menu> getMenusForCurrentUser(String username) throws SystemException {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        List<Menu> menus = null;
        try {
            List<Menu> sourceMenus = menuDAO.getUserMenuByUserName(username);
            menus = TreeUtil.transferToTree(sourceMenus);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return menus;
    }

    @Override
    public List<Integer> getMenuButtonId(Integer menuId) {

        return menuDAO.getMenuButton(menuId);
    }
}
