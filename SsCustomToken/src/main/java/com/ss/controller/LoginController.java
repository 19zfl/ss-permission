package com.ss.controller;


import com.ss.domain.User;
import com.ss.service.ILoginService;
import com.ss.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName:LoginController
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 17:26
 */

@RestController
public class LoginController {

    @Resource
    private ILoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
