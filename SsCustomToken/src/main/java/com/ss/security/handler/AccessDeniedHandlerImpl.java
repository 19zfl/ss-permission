package com.ss.security.handler;

import com.alibaba.fastjson.JSON;
import com.ss.utils.ResponseResult;
import com.ss.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:AccessDeniedHandlerImpl
 * @Description:没有权限处理
 * @Author:zfl19
 * @CreateDate:2024/10/25 21:27
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult responseResult =
                new ResponseResult(HttpStatus.FORBIDDEN.value(), "没有权限。");
        String unAuthorized = JSON.toJSONString(responseResult);
        WebUtils.renderString(httpServletResponse, unAuthorized);
    }
}
