package com.it.academy.library.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringAntMatchers("/logout"))
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .antMatchers(
                                "/", "/login", "/registration", "/error",
                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                                "/styles/css/**", "/images/**", "/js/**"
                        ).permitAll()
                        .antMatchers(HttpMethod.POST, "/users").permitAll()
                        .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/users/*/delete").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/books")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
