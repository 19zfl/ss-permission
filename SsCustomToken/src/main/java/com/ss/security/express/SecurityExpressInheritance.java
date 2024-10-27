package com.ss.security.express;

import com.ss.service.impl.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @ClassName:express
 * @Description:自定义权限注解字段
 * @Author:zfl19
 * @CreateDate:2024/10/25 22:50
 */

@Component("ss")
public class SecurityExpressInheritance {
    public boolean hasAuthority(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getPermissions().contains(authority);
    }
}
