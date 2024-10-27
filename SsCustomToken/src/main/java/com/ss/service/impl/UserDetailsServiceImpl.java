package com.ss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ss.domain.User;
import com.ss.mapper.MenuMapper;
import com.ss.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName:UserDetailsServiceImpl
 * @Description:
 *      实现UserDetailsService接口获取用户数据并返回给Authentication对象
 * @Author:zfl19
 * @CreateDate:2024/10/23 12:52
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> queryWrapper = userQueryWrapper.eq(User::getUserName, username);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", username);
        // 查询用户信息
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("账号或密码错误。");
        }
        // 查询对应的权限信息
//        List<String> authoritiesList = Arrays.asList("test", "list");
        List<String> authoritiesList = menuMapper.selectPermissionByUserId(user.getId());
        // 将数据封装程UserDetails进行返回
        return new LoginUser(user, authoritiesList);
    }
}
