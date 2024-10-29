package com.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * @ClassName:SecurityConfig
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/27 19:51
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(authenticationSuccessHandler)// 配置认证成功
                .failureHandler(authenticationFailureHandler);// 配置认证失败
        http.logout()
                .logoutSuccessHandler(logoutSuccessHandler);// 配置退出登录处理器
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests((requests -> requests.anyRequest().authenticated()));
    }
}
