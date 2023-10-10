package com.example.CarArchive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean()
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users", "/users/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/cars").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars", "/cars/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cars/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cars/{id}").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/cars/user/{id}").hasAuthority("USER"))


                //.anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                //.logout(logout -> logout.logoutUrl("/logout"))
                .build();
    }
}
