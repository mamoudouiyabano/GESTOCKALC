package com.alc.gestock.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description(" Gestion de Stock API documentation")
                                .title("Gestion de Stock REST API")
                                .build()
                )
                .groupName("Version 1 API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alc.gestock"))
                .paths(PathSelectors.ant(APP_ROOT +  "/**"))
                .build();
    }
}
