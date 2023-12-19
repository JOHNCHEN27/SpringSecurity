package com.lncanswer.security.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LNC
 * @version 1.0
 * @description
 * @date 2023/12/19 11:27
 */
@SpringBootApplication
@EnableEurekaServer //开启eureka注册中心服务
public class DiscoveryServer {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServer.class,args);
    }
}
