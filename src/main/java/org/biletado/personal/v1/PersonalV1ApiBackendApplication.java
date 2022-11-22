package org.biletado.personal.v1;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.biletado.personal.v1", "org.biletado.personal.v1.api" , "org.biletado.personal.v1.configuration"})
public class PersonalV1ApiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalV1ApiBackendApplication.class, args);
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}