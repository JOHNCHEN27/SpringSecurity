package com.lncanswer.security.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LNC
 * @version 1.0
 * @description SpringBoot启动类
 * @date 2023/12/15 16:48
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //排除数据库自动装配
public class SecuritySprIngBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SecuritySprIngBootApp.class,args);
    }
}
