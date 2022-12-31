package org.biletado.personal.v1.configuration;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Configuration
    public class ApplicationConfiguration {

        @Bean
        public KeycloakConfigResolver KeycloakConfigResolver() {
            return new KeycloakSpringBootConfigResolver();
        }
    }
}
