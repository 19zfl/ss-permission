package com.ss.security.handler;

import com.alibaba.fastjson.JSON;
import com.ss.utils.ResponseResult;
import com.ss.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:AuthenticationEntryPoint
 * @Description:认证失败处理
 * @Author:zfl19
 * @CreateDate:2024/10/25 21:17
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult responseResult =
                new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登录。");
        String unAuthorized = JSON.toJSONString(responseResult);
        WebUtils.renderString(httpServletResponse, unAuthorized);
    }
}
