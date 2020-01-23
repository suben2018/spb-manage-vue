package com.itsu.spbmanagevue.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author 苏犇
 * @create time 2020/1/9 23:22
 */
@Data
@TableName("tb_menu")
public class Menu {

    @TableId("menu_id")
    private Integer menuId;

    @TableField("menu_name")
    private String menuName;

    @TableField("url")
    private String url;

    @TableField("pid")
    private Integer pid;

    @TableField("icon")
    private String icon;

    @TableField(exist = false)
    private Set<Button> buttons;

    @TableField(exist = false)
    List<Menu> childrenMenus;
}
