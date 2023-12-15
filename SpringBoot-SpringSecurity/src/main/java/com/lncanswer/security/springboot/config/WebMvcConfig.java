package com.lncanswer.security.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LNC
 * @version 1.0
 * @description SpringMvc配置
 * @date 2023/12/15 16:54
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //默认Url根路径跳转到/login，此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
    }

}
