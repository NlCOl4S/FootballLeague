package com.futbolliga.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Football League API", version = "1.0"),
        security = @SecurityRequirement(name = "bearerAuth") //Aplica el candado a todos los endpoints
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP, //Viaja mediante HTTP
        scheme = "bearer", //Bearer token
        bearerFormat = "JWT" //Recibe JWT
)
public class OpenApiConfig {
}