package com.project.softwave.backend_SoftWave.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Documentação Swagger da API SoftWave",
                description = "API REST desenvolvida para um sistema de gestão de clientes e processos jurídicos",
                contact = @Contact(
                        name = "Leticia Fonseca",
                        url = "https://github.com/SoftWave-SPTech/backend-softwave",
                        email = "leticia.fonseca@sptech.school"
                ),
                license = @License(name = "UNLICENSED"),
                version = "1.0.0"
        )
)
@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)
public class OpenApiConfig {

}
