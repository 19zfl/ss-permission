# ss-permission

![image-20241029093842998](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202410290938110.png)

SpringBoot整合SpringSecurity完成权限校验

对于登录接口允许匿名访问

登录之后将用户数据，权限信息封装在LoginUser对象中，将用户id作为key，LoginUser对象为value存入Redis中

每次访问接口需要对请求头中的token进行校验，校验通过后将token解析为用户id

将用户id作为key获取Redis中的LoginUser对象，拿到个人信息和权限信息

然后再对每个接口进行权限访问
