package com.lncanswer.security.springboot.model;

import lombok.Data;

/**
 * @author LNC
 * @version 1.0
 * @description User实体类
 * @date 2023/12/15 20:28
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
