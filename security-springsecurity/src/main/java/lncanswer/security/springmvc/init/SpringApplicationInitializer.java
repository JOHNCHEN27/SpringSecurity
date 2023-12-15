package lncanswer.security.springmvc.init;

import lncanswer.security.springmvc.config.ApplicationConfig;
import lncanswer.security.springmvc.config.WebMvc;
import lncanswer.security.springmvc.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author LNC
 * @version 1.0
 * @description Spring启动 加载容器
 * @date 2023/12/15 10:12
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //指定rootContext的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //spring容器启动时 加载哪些配置
        return new Class<?>[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    //指定servletContext的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvc.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
