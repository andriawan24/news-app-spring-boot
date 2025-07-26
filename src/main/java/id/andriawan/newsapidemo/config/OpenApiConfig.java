package id.andriawan.newsapidemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "News Demo API",
                version = "v1",
                description = "This is a demonstration API for a news application."
        ),
        security = @SecurityRequirement(name = "X-API-KEY") // Applies API key security globally
)
@SecurityScheme(
        name = "X-API-KEY", // Matches the name in @SecurityRequirement
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        description = "API key for authentication"
)
public class OpenApiConfig {
}
