package com.lncanswer.security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LNC
 * @version 1.0
 * @description 单元测试类
 * @date 2023/12/15 19:28
 */
@RunWith(SpringRunner.class)
public class TestBCrypt {

    /**
     * 测试SpringSecurity BCrypt密码加密方式
     */
    @Test
    public void test1(){
        //对原始密码加密 随机生成一个salt对密码进行加密
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);
        //校验原始密码和BCrypt密码是否一致
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$x/eJV1LCiC9lljFJYOrQCueKS0i.1IgzITJ0cC1WIU4AJ5We8HRk2");
        System.out.println(checkpw);
    }
}
