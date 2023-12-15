package com.lncanswer.security.springboot.mapper;

import com.lncanswer.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LNC
 * @version 1.0
 * @description
 * @date 2023/12/15 20:29
 */

@Repository
public class UserMapper {

    @Resource
    JdbcTemplate jdbcTemplate;
    public UserDto getUserByUsername(String username){
        String sql ="select id,username,password,fullname from t_user where username = ?";
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new
                BeanPropertyRowMapper<>(UserDto.class));
        if(list == null && list.size() <= 0){
            return null;
        }
        return list.get(0);
    }

}
