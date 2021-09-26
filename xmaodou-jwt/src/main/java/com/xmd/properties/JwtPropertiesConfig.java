package com.xmd.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/5/31 下午5:17
 */
@Configuration
@EnableConfigurationProperties({JwtProperties.class})
public class JwtPropertiesConfig {
}
