package com.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName:CorsConfig
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/25 21:47
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许Cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 设置允许的Header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
