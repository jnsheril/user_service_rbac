package com.scaler.userservice_rbac.config;


import com.scaler.userservice_rbac.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests)->requests
                .requestMatchers("/users/register").permitAll()
                .requestMatchers("/users/login").permitAll()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers("/users/{userId}/roles/").hasRole("ADMIN")
                .requestMatchers("/roles/assign-permissions").hasRole("ADMIN")
                .requestMatchers("/roles/").hasRole("ADMIN")
                .requestMatchers("/permissions/").hasRole("ADMIN")
                .requestMatchers("/permissions/role/{roleName}").hasRole("ADMIN")
        ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();


    }
    @Bean
    public UserDetailsService userDetailsService(MyUserDetailService myUserDetailService) {
        return myUserDetailService; // Ensure MyUserDetailService is a Spring Bean
    }

    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailService myUserDetailService){
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(myUserDetailService);
      provider.setPasswordEncoder(new BCryptPasswordEncoder());
      return provider;
  }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}