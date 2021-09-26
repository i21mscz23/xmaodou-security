package com.xmd.user;

import com.xmd.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 14:44
 */
public class JdbcDetailsService implements UserDetailsService {


    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return securityService.check(username);
    }
}
