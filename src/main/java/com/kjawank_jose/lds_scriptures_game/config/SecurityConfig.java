package com.kjawank_jose.lds_scriptures_game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de autorización usando el nuevo API
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**",
                                "/game/**", "/leaderboard", "/history", "/error").permitAll()
                        .requestMatchers("/h2-console/**").permitAll() // Para H2 Console si la usas
                        .anyRequest().authenticated()
                )

                // Configuración de headers moderna
                .headers(headers -> headers
                        // Reemplazo para frameOptions().disable()
                        .frameOptions(frameOptions -> frameOptions.deny())
                        // O si necesitas permitir frames (para H2 console por ejemplo):
                        // .frameOptions(frameOptions -> frameOptions.sameOrigin())

                        // Configuraciones adicionales de seguridad de headers (corregidas)
                        .contentTypeOptions(contentTypeOptions -> {
                            // No necesitas llamar .and() aquí
                        })
                        .httpStrictTransportSecurity(hstsConfig -> hstsConfig
                                .maxAgeInSeconds(31536000)
                                .includeSubDomains(true) // Método corregido: includeSubDomains en lugar de includeSubdomains
                        )
                        .referrerPolicy(referrerPolicy -> referrerPolicy
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                        )
                )

                // Deshabilitar CSRF para el juego (ya que usa forms simples)
                .csrf(AbstractHttpConfigurer::disable)

                // Configuración de logout (opcional)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}