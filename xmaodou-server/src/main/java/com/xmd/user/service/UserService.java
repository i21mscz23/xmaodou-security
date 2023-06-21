package com.xmd.user.service;

import com.xmd.user.entity.User;
import com.xmd.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryByUsername(String username){

        return userMapper.queryByUsername(username);
    }
}
