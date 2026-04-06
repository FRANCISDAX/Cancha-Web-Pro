package com.cibersoftcys.canchawebpro.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("CANCHA WEB PRO")
                .description("Servicios REST para gestión de Alquiler de Canchas Deportivas")
                .version("v1.0"));
    }

}
