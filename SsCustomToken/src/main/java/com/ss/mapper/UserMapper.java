package com.ss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName:UserMapper
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 12:35
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
