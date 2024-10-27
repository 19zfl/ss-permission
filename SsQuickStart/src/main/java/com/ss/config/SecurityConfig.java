package com.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(authenticationSuccessHandler);
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests((requests -> requests.anyRequest().authenticated()));
    }
}
