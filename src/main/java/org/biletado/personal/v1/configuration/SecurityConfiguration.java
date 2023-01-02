
package org.biletado.personal.v1.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.servlet.http.HttpServletResponse;


@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerURI;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwksSetUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.GET,"/personal/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/error").permitAll()
                        .antMatchers(HttpMethod.POST).authenticated()
                        .antMatchers(HttpMethod.PUT).authenticated()
                        .antMatchers(HttpMethod.DELETE).authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    @Bean JwtDecoder jwtDecoder()
    {
        return JwtDecoders.fromIssuerLocation(issuerURI);
        //return NimbusJwtDecoder.withJwkSetUri(jwksSetUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }
}