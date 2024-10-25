package com.ss.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ss.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:mapper
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 12:46
 */

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void apiTest() {
        List<User> users = userMapper.selectList(null);
        users.stream().forEach(System.err::print);
    }

    @Test
    public void mybatisTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", "user01");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encode = passwordEncoder.encode("123456");
//        System.out.println(encode);
        // $2a$10$ZtZ5woDdfSH8gs0rQWS3D.YDe0JMJmrx5LZSZOyUGecE28jy7UEWa
        boolean matches = passwordEncoder.matches("123456", "$2a$10$ZtZ5woDdfSH8gs0rQWS3D.YDe0JMJmrx5LZSZOyUGecE28jy7UEWa");
        System.out.println(matches);
    }

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void check() {
        List<String> strings = menuMapper.selectPermissionByUserId(1L);
        strings.stream().forEach(System.err::println);
    }

}
