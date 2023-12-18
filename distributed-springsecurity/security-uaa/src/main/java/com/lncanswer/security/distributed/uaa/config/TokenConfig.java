package com.lncanswer.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author LNC
 * @version 1.0
 * @description 令牌存储策略
 * @date 2023/12/18 16:49
 */
@Configuration
public class TokenConfig {

    /**
     * 令牌服务: InMemoryTokenStore、JdbcTokenStore、JwtTokenStore
     * 默认采用InMemoryTokenStore
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        //暂时采用InMemoryTokenStore
        return  new InMemoryTokenStore();
    }
}
