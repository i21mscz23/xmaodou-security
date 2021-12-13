package com.xmd;

import com.xmd.properties.SecuritySocialProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/17 下午7:37
 */
@SpringBootApplication
@EnableConfigurationProperties({SecuritySocialProperties.class})
public class SocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class,args);
    }
}
