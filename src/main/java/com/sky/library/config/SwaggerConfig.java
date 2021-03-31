package com.sky.library.config;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Divya Prabakaran
 **/

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        final Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Sky Book Library API")
                .description("Sky Book API reference for developers")
                .termsOfServiceUrl("https://sky.com")
                .licenseUrl("divyaprabakaranpd@gmail.com").version("1.0").build();
    }
}
