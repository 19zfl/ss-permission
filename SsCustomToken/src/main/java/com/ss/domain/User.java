package com.ss.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:User
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 0:53
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private Long id;

    @TableField("user_name")
    private String userName;
    private String nickName;
    private String password;
    private String status;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
    private String userType;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer delFlag;

}
