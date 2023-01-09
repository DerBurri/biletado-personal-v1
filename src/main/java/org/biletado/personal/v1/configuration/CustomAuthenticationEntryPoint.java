package org.biletado.personal.v1.configuration;

import org.biletado.personal.v1.api.ApiUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    @ExceptionHandler(value = {InvalidBearerTokenException.class})
    public void commence(HttpServletRequest req, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream responseStream = response.getOutputStream();
        responseStream.print(ApiUtil.jsonMessage(authException.getMessage()));
        responseStream.flush();
    }
}
