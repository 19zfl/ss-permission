package com.ss.service.impl;

import com.ss.domain.User;
import com.ss.service.ILoginService;
import com.ss.utils.JwtUtil;
import com.ss.utils.RedisService;
import com.ss.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @ClassName:LoginServiceImpl
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 17:30
 */

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        // 获取AuthenticationManager进行用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败。");
        }
        // 使用userid生成token
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.getJwtToken(userId);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", token);
        redisService.setStringKeyAndValue(userId, loginUser);
        return new ResponseResult(200, "登录成功。", hashMap);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Long id = loginUser.getUser().getId();
        // 删除redis中的值
        redisService.deleteRedisByKey(String.valueOf(id));
        return new ResponseResult(200, "已退出登录。");
    }
}
