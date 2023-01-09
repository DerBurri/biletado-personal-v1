package org.biletado.personal.v1;

import org.biletado.logging.RequestLoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
//import com.fasterxml.jackson.databind.Module;
//import org.openapitools.jackson.nullable.JsonNullableModule;

@SpringBootApplication
@ComponentScan(basePackages = {"org.biletado.personal.v1", "org.biletado.personal.v1.api", "org.biletado.personal.v1.configuration"})
@EntityScan(basePackages = {"org.biletado.personal.v1.model"})
public class PersonalV1ApiBackendApplication {

    public static void main(String[] args) {
        System.out.println("\n\n STARTING SPRING BOOT SERVER");
        SpringApplication.run(PersonalV1ApiBackendApplication.class, args);
    }

//    @Bean
//    public Module jsonNullableModule() {
//        return new JsonNullableModule();
//    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
                errorAttributes.remove("trace");
                errorAttributes.remove("status");
                errorAttributes.remove("timestamp");
                errorAttributes.remove("path");
                errorAttributes.remove("error");
                return errorAttributes;
            }

        };
    }

    @Bean
    public RequestLoggingFilter logFilter() {
        RequestLoggingFilter filter = new RequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        return filter;
    }

}