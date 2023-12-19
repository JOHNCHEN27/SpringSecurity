package com.lncanswer.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author LNC
 * @version 1.0
 * @description 令牌存储策略
 * @date 2023/12/18 16:49
 */
@Configuration
public class TokenConfig {

    //对称密码
    private String SIGNING_KEY = "uaalnc";

    /**
     * 令牌服务: InMemoryTokenStore、JdbcTokenStore、JwtTokenStore
     * 默认采用InMemoryTokenStore
     * @return
     */
//    @Bean
//    public TokenStore tokenStore(){
//        //暂时采用InMemoryTokenStore
//        return  new InMemoryTokenStore();
//    }


    //采用JWT令牌存储
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    //设置jwt对称密钥
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //采用对称密钥进行加密 资源服务用此密钥验证
        return converter;
    }
}
