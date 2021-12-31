package com.xmd.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/24 下午2:34
 */
@Component
public class JdbcUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return User.withUsername("admin")
                .password("$2a$10$G4sG6eM9nxjNjnqCoi7Zgu0fLZ3bWmJ8ox2.PbFDRedPT/GmfGLyG")
                .authorities("ROLE_ADMIN")
                .build();
    }
}
