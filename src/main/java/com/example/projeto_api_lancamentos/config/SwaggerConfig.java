package com.example.projeto_api_lancamentos.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Lançamentos")
                        .description("API para operações de crédito e débito em contas bancárias específicas como desafio da Matera.")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa")
                        .url("https://github.com/GabriellaFerr00/projeto-api-lancamentos"));
    }
}
