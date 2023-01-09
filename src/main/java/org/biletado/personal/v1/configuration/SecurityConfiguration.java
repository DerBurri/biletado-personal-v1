
package org.biletado.personal.v1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@Profile("prod")
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                                .antMatchers(HttpMethod.GET, "/personal/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/error").permitAll()
//                        .antMatchers(HttpMethod.POST).authenticated()
//                        .antMatchers(HttpMethod.PUT).authenticated()
//                        .antMatchers(HttpMethod.DELETE).authenticated()
                        .antMatchers(HttpMethod.POST).permitAll()
                        .antMatchers(HttpMethod.PUT).permitAll()
                        .antMatchers(HttpMethod.DELETE).permitAll()
                )
                .csrf().disable()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)

                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint);

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        //return JwtDecoders.fromIssuerLocation()
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }
}