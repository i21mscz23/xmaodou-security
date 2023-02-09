package com.xmd.social.config;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/17 下午2:10
 */
@Component
public class CreateConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //通过第三方返回的信息作存储为用户信息（userconnection中的userId）
        //TODO 用户登录的后的情况，返回用户编号；用户未登录返回-1或providerUserId
        return connection.getKey().getProviderUserId();
    }
}
