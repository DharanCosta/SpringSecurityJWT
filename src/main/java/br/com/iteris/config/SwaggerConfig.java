package br.com.iteris.config;


import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.iteris.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Java Spring JWT - Authentication",
                "Comunidades Iteris - Spring Boot - Java Spring JWT - Authentication",
                "1.0.0",
                "Disponível para estudos",
                new Contact("Comunidades Iteris", "https://iteris1.sharepoint.com/sites/universidade", "organizacao.td@iteris.com.br"),
                "", "", Collections.emptyList());
    }
}