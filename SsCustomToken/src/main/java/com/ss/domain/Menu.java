package com.ss.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:Menu
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/25 17:24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;
    @TableId
    private Long id;
    @TableField("menu_name")
    private String menuName;
    private String path;
    private String component;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private String createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("udpate_time")
    private String updateTime;
    @TableField("del_flag")
    private String delFlag;
    private String remark;

}
