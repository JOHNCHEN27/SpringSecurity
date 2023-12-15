package com.lncanswer.security.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author LNC
 * @version 1.0
 * @description SpringSecurity配置
 * @date 2023/12/15 16:59
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //配置用户服务信息
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        //硬编码创建用户 --实际中要从数据库查询
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("123").authorities("p2").build());
//        return manager;
//    }

    //密码编码方式
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //这里暂时采用明文编码方式（按字符串进行编码 用户输入123 就是123） 实际不推荐
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //采用加密编码 对密码进行加密
        return new BCryptPasswordEncoder();
    }

    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable() //屏蔽拦截csrf跨站请求
                .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1") //访问r1路径必须用p1权限
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated() //拦截/r/**路径 需要认证
                .anyRequest().permitAll() //其他路径请求全部放行
                .and()
                .formLogin() //允许表单登录
                .loginPage("/login-view") //指定自己的登录页面
                .loginProcessingUrl("/login")//指定处理的url 提交表单的路径
                .successForwardUrl("/login-success"); //自定义表单登录页面地址跳转
    }
}
