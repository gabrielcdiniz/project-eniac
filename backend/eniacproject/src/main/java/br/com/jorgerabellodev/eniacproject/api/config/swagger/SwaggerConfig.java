package br.com.jorgerabellodev.eniacproject.api.config.swagger;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Eniac project store Rest API")
                .description("Use this Rest API to manage a ficticious store")
                .license("Unlicense")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact(
                        "Jorge Rabello",
                        "https://www.linkedin.com/in/jorgerabellodev/",
                        "jorge.rabello3@gmail.com")
                ).build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.jorgerabellodev.eniacproject.api.endpoints"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
}
