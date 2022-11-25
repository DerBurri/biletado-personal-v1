package org.biletado.personal.v1;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;

@SpringBootApplication
@ComponentScan(basePackages = {"org.biletado.personal.v1", "org.biletado.personal.v1.api", "org.biletado.personal.v1.configuration"})
public class PersonalV1ApiBackendApplication {
    public static String[] EnvironmentVariableNames = new String[]{
            "POSTGRES_PERSONAL_HOST", "POSTGRES_PERSONAL_PORT", "POSTGRES_PERSONAL_DBNAME",
            "POSTGRES_PERSONAL_USER", "POSTGRES_PERSONAL_PASSWORD"
    };

    public static HashMap<String, String> EnvironmentVariables = new HashMap<>();


    public static void main(String[] args) {
        System.out.println("READING ENVIRONMENT VARIABLES");
        for (String environmentVariableName : EnvironmentVariableNames) {
            String val = System.getenv(environmentVariableName);
            EnvironmentVariables.put(environmentVariableName, val);
            System.out.println(environmentVariableName + ": " + val);
        }

        System.out.println("\n\n STARTING SPRING BOOT SERVER");
        SpringApplication.run(PersonalV1ApiBackendApplication.class, args);
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}