package com.lncanswer.security.distributed.order.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lncanswer.security.distributed.order.common.EncryptUtil;
import com.lncanswer.security.distributed.order.model.UserDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LNC
 * @version 1.0
 * @description
 * @date 2023/12/19 19:49
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("json-token");

        if (token != null){
            //解析token
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            JSONObject userJSON = JSON.parseObject(json);
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUsername(userJSON.getString("principal"));
            JSONArray authoritiesArray = userJSON.getJSONArray("authorities");
            UserDTO userDTO = JSON.parseObject(userJSON.getString("principal"), UserDTO.class);
            //转化为权限数组
            String[] authorities = authoritiesArray.toArray(new String[authoritiesArray.size()]);

            //新建并填充authentication
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDTO, null, AuthorityUtils.createAuthorityList(authorities));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //将authentication保存至安全上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
