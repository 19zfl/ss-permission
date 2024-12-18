package com.ss.controller;

import com.ss.utils.ResponseResult;
import org.apache.coyote.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:HelloController
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/22 23:26
 */

@RestController
public class HelloController {

    @RequestMapping("/custom")
    @PreAuthorize("@ss.hasAuthority('system:dept:list')")
    public ResponseResult custom() {
        return new ResponseResult(200, "访问成功", null);
    }

    @RequestMapping("/dept")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/emp")
    @PreAuthorize("hasAuthority('system:emp:list')")
    public String list() {
        return "list";
    }

    @RequestMapping("/test")
    @PreAuthorize("hasAuthority('test')")
    public String test() {
        return "test";
    }

}
