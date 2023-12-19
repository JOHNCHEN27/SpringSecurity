package com.lncanswer.security.distributed.order.controller;

import com.lncanswer.security.distributed.order.model.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LNC
 * @version 1.0
 * @description
 * @date 2023/12/18 20:23
 */
@RestController
public class OrderController {

//    @GetMapping(value = "/r1")
//    @PreAuthorize("hasAnyAuthority('p1')")
//    public String r1(){
//        return "访问资源1";
//    }


    @PreAuthorize("hasAuthority('p1')")
    @GetMapping(value = "/r1")
    public String r1(){
        UserDTO user = (UserDTO)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源1";
    }
    @PreAuthorize("hasAuthority('p2')")
    @GetMapping(value = "/r2")
    public String r2(){//通过Spring Security API获取当前登录用户
        UserDTO user =
                (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源2";
    }

}
