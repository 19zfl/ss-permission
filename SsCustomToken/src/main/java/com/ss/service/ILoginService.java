package com.ss.service;

import com.ss.domain.User;
import com.ss.utils.ResponseResult;

/**
 * @ClassName:ILoginService
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 17:29
 */

public interface ILoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
