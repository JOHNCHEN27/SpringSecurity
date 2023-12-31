package com.lncanswer.security.springboot.service;

import com.lncanswer.security.springboot.mapper.UserMapper;
import com.lncanswer.security.springboot.model.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LNC
 * @version 1.0
 * @description 自定义SpringSecurity中的UserDetailService
 * @date 2023/12/15 17:41
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    /**
     * 重写UserDetailService方法
     * @param username the username identifying the user whose data is required.
     *
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(java.lang.String username) throws UsernameNotFoundException {
        //        //登录账号
//        System.out.println("username="+username);
//       //根据账号去数据库查询...
//       //这里暂时使用静态数据
//        UserDetails userDetails =
//                User.withUsername(username).password("123").authorities("p1").build();
//        return userDetails;

        UserDto user = userMapper.getUserByUsername(java.lang.String.valueOf(username));
        if (user != null){
            System.out.println("拿到张三用户");
            //赋予权限
            List<java.lang.String> permissions = userMapper.findPermissionsByUserId(user.getId());
            String[] strings = new String[permissions.size()];
            permissions.toArray(strings);
            return User.withUsername(user.getUsername()).password(user.getPassword()).authorities(strings).build();
        }
        //没有用户则返回null
        System.out.println("为查询到用户");
        return null;
    }
}
