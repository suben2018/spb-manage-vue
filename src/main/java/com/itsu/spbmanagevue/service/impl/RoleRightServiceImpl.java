package com.itsu.spbmanagevue.service.impl;

import com.itsu.spbmanagevue.components.exception.SystemException;
import com.itsu.spbmanagevue.dao.MenuDAO;
import com.itsu.spbmanagevue.service.RoleRightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author suben
 * @create time 2020/1/25 16:53
 */
@Service
public class RoleRightServiceImpl implements RoleRightService {
    @Resource
    private MenuDAO menuDAO;

    @Override
    public List<HashMap> getRoleRightList() throws Exception {
        List<HashMap> menuButtonInfo = null;
        try {
            menuButtonInfo = menuDAO.getMenuButtonInfo();
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return menuButtonInfo;
    }
}
