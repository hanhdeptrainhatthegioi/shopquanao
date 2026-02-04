package com.poly.shopquanao.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public
                        .requestMatchers(
                                "/",
                                "/home",
                                "/register",
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // Admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // User
                        .requestMatchers("/user/**").hasRole("USER")

                        .anyRequest().authenticated()
                )

                .formLogin(login -> login
                        .loginPage("/login")

                        // 🔥 QUAN TRỌNG
                        .loginProcessingUrl("/login")

                        .defaultSuccessUrl("/redirect", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // 🔥 GẮN DB AUTH
                .userDetailsService(customUserDetailService);

        return http.build();
    }

}
