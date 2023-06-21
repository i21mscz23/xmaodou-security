package com.xmd.custom;

import com.xmd.properties.JwtProperties;
import com.xmd.service.SecurityService;
import com.xmd.service.impl.DefaultSecurityService;
import com.xmd.user.JwtUserDetails;
import com.xmd.user.entity.User;
import com.xmd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityService extends DefaultSecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public JwtUserDetails check(String username) {

        User user = userService.queryByUsername(username);
        if(user == null){
            throw new BadCredentialsException("账号或密码错误");
        }
        return new JwtUserDetails(username,user.getPassword(),
                jwtProperties.getJwtExpiration(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"),null);
    }
}
