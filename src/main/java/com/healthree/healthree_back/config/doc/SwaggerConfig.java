package com.healthree.healthree_back.config.doc;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/**", "/user/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        List<Server> serverList = new ArrayList<>();
        serverList.add(new Server().url("https://dev-api.thegated.co.kr"));
        serverList.add(new Server().url("http://localhost:8080"));
        return new OpenAPI().servers(serverList)
                .info(new Info().title("THE GATED API")
                        .description("THE GATED 서비스를 위한 API 명세서")
                        .version("v0.0.1"));
    }
}
