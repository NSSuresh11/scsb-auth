package org.recap.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by hemalathas on 22/8/16.
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    public static final String SCHEME_NAME = "apiKey";

    @Bean
    public Info metadata() {
        return new Info().title("SCSB APIs")
                .description("APIs to interact with SCSB middleware are RESTful and need an API_KEY for any call to be invoked. Further NCIP protocols are also supported")
                .version("v1.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }

    @Bean
    public OpenAPI openAPI() {
        var openApi = new OpenAPI()
                .info(metadata());
        addSecurity(openApi);
        return openApi;
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("springshop-admin")
                .pathsToMatch("*")
                .packagesToScan("org.recap.controller")
                .build();
    }

    private void addSecurity(OpenAPI openApi) {
        var components = createComponents();
        var securityItem = new SecurityRequirement().addList(SCHEME_NAME);
        openApi
                .components(components)
                .addSecurityItem(securityItem);
    }


    private Components createComponents() {
        var components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, apiKey());

        return components;
    }

    @Bean
    public SecurityScheme apiKey() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name("api_key")
                .in(SecurityScheme.In.HEADER);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SwaggerInterceptor())
                .addPathPatterns("/authentication/*");
     }




}
