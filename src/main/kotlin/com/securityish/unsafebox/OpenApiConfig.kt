package com.securityish.unsafebox

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Unsafebox API")
                    .version("0.0.1-SNAPSHOT")
                    .description("SafeIsh API - The most (in) secure safebox")
            )
    }
}
