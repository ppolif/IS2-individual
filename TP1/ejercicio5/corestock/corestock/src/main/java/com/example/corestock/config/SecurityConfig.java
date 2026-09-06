package com.example.corestock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(jakarta.servlet.DispatcherType.ERROR).permitAll()
                        // Liberamos los recursos de Mazer y las vistas de acceso público
                        .requestMatchers("/assets/**", "/login", "/registro").permitAll()
                        // Cualquier otra petición requiere login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/inventario");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                // Apagamos la protección CSRF temporalmente para que tu formulario POST funcione
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
