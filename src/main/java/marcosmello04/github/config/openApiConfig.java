package marcosmello04.github.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class openApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("REST API's RESTful")
                .version("v1")
                .description("with Java, Spring Boot, Kubernetes and Docker")
                .termsOfService("https://github.com/marcosmello04")
                .license(new License().name("Apache 2.0")));
    }
}
