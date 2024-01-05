package com.CBSEGroup11pos.auth.config;

import com.CBSEGroup11pos.auth.config.securityToken.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ApplicationConfig applicationConfig;
    private final JwtAuthFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(config -> config
                .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
        ).csrf(AbstractHttpConfigurer::disable
        ).authorizeHttpRequests(requests -> {
                    requests.requestMatchers("/register/**", "/authenticate/**", "/user/**").permitAll();
                    requests.anyRequest().authenticated();
                }
//        ).authorizeHttpRequests(requests -> requests
//                .requestMatchers("/users/**")
//                .hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated()
        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(applicationConfig.authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
