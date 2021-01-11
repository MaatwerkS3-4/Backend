package com.ProftaakS34.Opinion.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static io.swagger.models.auth.In.HEADER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@EnableSwagger2
@Controller
@ApiIgnore
public class SwaggerConfig {

    private final static String API_TITLE = "API for Viewpoint";
    private final static String API_DESCRIPTION = "The API description for all of Viewpoint's endpoints";
    private final static String API_VERSION = "0.1.0";

    @GetMapping("/api")
    public String swagger(){
        return "redirect:/swagger-ui.html";
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title(API_TITLE)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION)
                        .build())
                .tags(
                        new Tag("Comments", "Endpoints for comment interaction"),
                        new Tag("Discussions", "Endpoints for discussion interaction"),
                        new Tag("DiscussionInfo", "Endpoints for info about discussions"),
                        new Tag("Users", "Endpoints for user interaction"),
                        new Tag("Categories", "Endpoints for category interaction")
                );
    }

}
