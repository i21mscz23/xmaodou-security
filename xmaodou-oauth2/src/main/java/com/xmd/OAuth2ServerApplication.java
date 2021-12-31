package com.xmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/15 下午4:29
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApplication.class, args);
    }

}
