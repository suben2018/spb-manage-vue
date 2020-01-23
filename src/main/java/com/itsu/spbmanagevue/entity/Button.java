package com.itsu.spbmanagevue.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 苏犇
 * @create time 2020/1/19 19:13
 */
@TableName("tb_button")
@Data
public class Button {

    @TableId("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("stat")
    private String stat;

}
