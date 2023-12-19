package com.lncanswer.security.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.lncanswer.security.gateway.utils.EncryptUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LNC
 * @version 1.0
 * @description token传递拦截
 * @date 2023/12/19 18:59
 */
public class AuthFilter extends ZuulFilter {

    //令牌解析成功之后进入此拦截器 保存用户和权限信息到Context上下文
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取令牌内容
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取资源用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)){
        // 无token访问网关内资源的情况,目前仅有uua服务直接暴露
            return null;
        }
        OAuth2Authentication auth2Authentication = (OAuth2Authentication)authentication;
        Authentication userAuthentication = auth2Authentication.getUserAuthentication();
        //拿到用户认证信息
        Object principal = userAuthentication.getPrincipal();
        //组装明文token 转发给微服务 放入header 名称为json-token
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(
                s-> authorities.add(((GrantedAuthority)s).getAuthority()));
        OAuth2Request oAuth2Request = auth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        HashMap<String, Object> jsonToken = new HashMap<>(requestParameters);
        if (userAuthentication != null){
            jsonToken.put("principal", userAuthentication.getName());
            jsonToken.put("authorities",authorities);
        }
        ctx.addZuulRequestHeader("json-token", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));
        return null;
    }
}
