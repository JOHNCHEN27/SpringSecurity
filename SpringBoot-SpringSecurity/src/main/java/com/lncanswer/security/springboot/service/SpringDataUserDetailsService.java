package com.lncanswer.security.springboot.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author LNC
 * @version 1.0
 * @description 自定义SpringSecurity中的UserDetailService
 * @date 2023/12/15 17:41
 */
public class SpringDataUserDetailsService implements UserDetailsService {

    /**
     * 重写UserDetailService方法
     * @param username the username identifying the user whose data is required.
     *
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录账号
        System.out.println("username="+username);
       //根据账号去数据库查询...
       //这里暂时使用静态数据
        UserDetails userDetails =
                User.withUsername(username).password("123").authorities("p1").build();
        return userDetails;

    }
}
