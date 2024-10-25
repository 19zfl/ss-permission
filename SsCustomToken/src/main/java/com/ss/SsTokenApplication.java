package com.ss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.ss.mapper")
public class SsTokenApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SsTokenApplication.class, args);
        System.out.println(run);
    }
}