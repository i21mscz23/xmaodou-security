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
        return connection.getKey().getProviderUserId();
    }
}
