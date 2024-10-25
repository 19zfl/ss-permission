package com.ss.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:WebUtils
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/10/23 0:49
 */

public class WebUtils {

    public static String renderString(HttpServletResponse res, String string) {
        try {
            res.setStatus(200);
            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");
            res.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}