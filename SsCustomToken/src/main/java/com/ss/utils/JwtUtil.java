package com.ss.utils;

import cn.hutool.core.lang.Chain;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName:JwtUtil
 * @Description: Jwt工具类
 * @Author:zfl19
 * @CreateDate:2024/10/22 23:49
 */

public class JwtUtil {

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsaW4tdXNlciIsImlhdCI6MTcyOTY4NDQ0MywiZXhwIjoxNzI5NzcwODQzLCJqdGkiOiIxIn0.kvjJno_nBZO3ucdY9Wl7NlnRhbusU6Tyi0tO0Gyu-hQ";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        System.out.println(body.getId());
    }

    /**
     * 两个常量： 过期时间；秘钥
     */
    public static final long EXPIRE = 1000*60*60*24;
    public static final String SECRET = "19zfl";

    /**
     * 生成token字符串的方法
     * @param id
     * @return
     */
    public static String getJwtToken(String id){
        // 签名算法，表示sha256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String JwtToken = Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token主体信息，存储用户信息
                .setId(id)
//                .claim("nickname", nickname)
                //.signWith(SignatureAlgorithm.ES256, SECRET)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @Param jwtToken
     */
    public static boolean checkToken(String jwtToken){
        if (StringUtils.isEmpty(jwtToken)){
            return false;
        }
        try{
            //验证token
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @Param request
     */
    public static boolean checkToken(HttpServletRequest request){
        try {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)){
                return false;
            }
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @Param request
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return (String) body.get("id");
    }

    public static Claims parseToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claims.getBody();
        return body;
    }

}
