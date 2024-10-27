package com.ss.security.filter;

import com.ss.service.impl.LoginUser;
import com.ss.utils.JwtUtil;
import com.ss.utils.RedisService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName:JwtAuthenticationTokenFilter
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 20:38
 */

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisService redisService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String id;
        try {
            // 解析token获取userId
            Claims claims = JwtUtil.parseToken(token);
            id = claims.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token校验未通过。");
        }
        LoginUser loginUser = redisService.getKeyObjectValue(id, LoginUser.class);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录。");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = loginUser.getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, simpleGrantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
