package com.lncanswer.security.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LNC
 * @version 1.0
 * @description 登录接口
 * @date 2023/12/15 17:02
 */
@RestController
public class LoginController {


    /**
     * 登录路径接口
     * @return
     */
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return getUserName() + " 登录成功";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        return getUserName() + " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        return getUserName() + " 访问资源2";
    }

    private String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()){
            //未认证
            return null;
        }
        //获取用户身份
        Object principal = authentication.getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails){
            //转化成userDetails
            userName = ((UserDetails) principal).getUsername();
            System.out.println("userName = " + userName);
        }else {
            //未转化成功 转为字符串
            userName =principal.toString();
            System.out.println("userName = " + userName);
        }
        return userName;
    }
}
