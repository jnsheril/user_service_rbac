package com.scaler.userservice_rbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/one", "/api/two").hasAnyRole("STAFF", "SUPERVISOR", "ADMIN")
                        .requestMatchers("/api/three").hasAnyRole("SUPERVISOR", "ADMIN")
                        .requestMatchers("/api/**").hasRole("ADMIN") // Admin has access to all APIs
                        .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .logout();

        return http.build();
    }

}
