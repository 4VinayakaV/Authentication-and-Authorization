package com.example.auth_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // 4 users and 4 roles 
        UserDetails TimUser = User.withUsername("Tim")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails BillAnalyst = User.withUsername("Bill")
                .password(encoder.encode("password"))
                .roles("ANALYST")
                .build();

        UserDetails MarkManager = User.withUsername("Mark")
                .password(encoder.encode("password"))
                .roles("MANAGER")
                .build();

        UserDetails SamAdmin = User.withUsername("Sam")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(TimUser, BillAnalyst, MarkManager, SamAdmin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // URL-based Authorization
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/user/**").hasAnyRole("USER","ANALYST","MANAGER","ADMIN")
                .requestMatchers("/api/analyst/**").hasAnyRole("ANALYST","MANAGER","ADMIN")
                .requestMatchers("/api/manager/**").hasAnyRole("MANAGER","ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .logout(Customizer.withDefaults())
            .csrf(Customizer.withDefaults());

        return http.build();
    }
}
