package com.xmd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title( "springDoc gateway API")
                .description( "springDoc gateway API")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("xmaodou")
                        .email("i21mscz23@gmail.com")));
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder().group("应用测试模块")
                .pathsToMatch("/jwt/**")
                .build();
    }




}
