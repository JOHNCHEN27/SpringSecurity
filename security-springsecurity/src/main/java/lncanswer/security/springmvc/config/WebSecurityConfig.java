package lncanswer.security.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * @author LNC
 * @version 1.0
 * @description SpringSecurity安全配置
 * @date 2023/12/15 10:29
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置用户服务信息
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //硬编码创建用户 --实际中要从数据库查询
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("123").authorities("p2").build());
        return manager;
    }

    //密码编码方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //这里暂时采用明文编码方式（按字符串进行编码 用户输入123 就是123） 实际不推荐
        return NoOpPasswordEncoder.getInstance();
    }

    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers("/r/**").authenticated() //拦截/r/**路径 需要认真
                .anyRequest().permitAll() //其他路径请求全部放心
                .and()
                .formLogin() //允许表单登录
                .successForwardUrl("/login-success"); //自定义表单登录页面地址跳转
    }

}
