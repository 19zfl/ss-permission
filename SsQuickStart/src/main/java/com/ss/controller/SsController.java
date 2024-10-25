package com.ss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:SsController
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/22 21:23
 */

@RestController
public class SsController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
