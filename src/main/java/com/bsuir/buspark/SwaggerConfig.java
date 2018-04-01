package com.bsuir.buspark;

import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.bsuir.buspark.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());

    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Bus park Spring Boot WebAPI",
                "Web Api provided for labs in BSUIR",
                "1.0",
                "Created by Ilya Kremniou",
                new springfox.documentation.service.Contact("Ilya Kremniou","http://vk.com/mc_ilia","Ilya_Kremniou@epam.com"),
                "No linence",
                "http://vk.com/mc_ilia",
                new ArrayList<>()
        );
        return apiInfo;
    }
}