package com.lncanswer.security.distributed.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1(){
        return "访问资源1";
    }

}
