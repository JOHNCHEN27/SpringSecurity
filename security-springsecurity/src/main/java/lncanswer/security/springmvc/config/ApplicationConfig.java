package lncanswer.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author LNC
 * @version 1.0
 * @description spring容器配置
 * @date 2023/12/15 10:04
 */
@Configuration //相当于applicationContext.xml
@ComponentScan(basePackages = "lncanswer.security.springmvc"
        ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {
    //在此配置除了Controller的其它bean，比如：数据库链接池、事务管理器、业务bean等。
}