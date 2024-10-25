package com.ss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName:UserMapper
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 12:35
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermissionByUserId(Long id);
}
