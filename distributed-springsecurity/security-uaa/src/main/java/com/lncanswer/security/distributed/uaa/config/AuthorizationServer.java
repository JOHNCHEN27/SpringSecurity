package com.lncanswer.security.distributed.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import static org.bouncycastle.cms.RecipientId.password;

/**
 * @author LNC
 * @version 1.0
 * @description 授权服务器配置
 * @date 2023/12/18 16:28
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    //注入令牌类型对象
    @Autowired
    private TokenStore tokenStore;

    //注入客户端服务对象
    @Autowired
    private ClientDetailsService clientDetailsService;

    //授权码对象
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    //认证管理器对象
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 重写配置客户端详细服务
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
// clients.withClientDetails(clientDetailsService);
        clients.inMemory()// 使用in‐memory存储
                .withClient("c1")// 用来标识客户的id
                .secret(new BCryptPasswordEncoder().encode("secret")) //客户端安全码
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code",
                        "password", "client_credentials", "implicit", "refresh_token")// 该client允许的授权类型authorization_code, password, refresh_token, implicit, client_credentials
                .scopes("all")// 允许的授权范围
                .autoApprove(false)//加上验证回调地址
                .redirectUris("http://www.baidu.com");
    }


    /**
     * 定义令牌管理服务
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices service=new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService); //令牌对应的客户端详情服务
        service.setSupportRefreshToken(true); //令牌刷新策略
        service.setTokenStore(tokenStore); //令牌类型
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    //设置授权模式授权码Bean如何存取
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //设置授权码模式的授权码如何存取，暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 令牌访问端点
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager) //认证管理器
                .authorizationCodeServices(authorizationCodeServices) //认证方式（授权码认证）
                .tokenServices(tokenServices()) //令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    /**
     * 令牌安全约束
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security
                .tokenKeyAccess("permitAll()") //oauth/token_key是公开
                .checkTokenAccess("permitAll()") //oauth/check_token公开
                .allowFormAuthenticationForClients(); //允许表单认证
    }


}
