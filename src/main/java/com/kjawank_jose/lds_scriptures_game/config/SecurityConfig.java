package com.kjawank_jose.lds_scriptures_game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // rutas públicas
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/game/**").permitAll()
                        .requestMatchers("/leaderboard/**").permitAll()
                        .requestMatchers("/history/**").permitAll()
                        // cualquier otra requiere login
                        .anyRequest().authenticated()
                )
                // deshabilitar CSRF y frameOptions para h2-console
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }
}
