package com.estudo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.estudo")).paths(PathSelectors.regex("/.*"))
                .build().apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo("API ESTUDO", "API ESTUDO", "1.0", "Termos para serviços",
                new Contact("https://www.linkedin.com/in/natanael-luiz-10/", "(83) 99366-9227", "natanael.10@hotmail.com"),
                "Apache License Version 2.0", "https://www.apache.org/licesen.html", new ArrayList<>());

        return apiInfo;
    }
}