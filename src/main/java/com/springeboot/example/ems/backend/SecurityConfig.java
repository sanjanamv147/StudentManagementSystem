package com.springeboot.example.ems.backend;

import com.springeboot.example.ems.backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig implements Security {
    @Autowired
    @Lazy
    private CustomUserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/users")).permitAll() // No authentication required for user creation
                        .requestMatchers(new AntPathRequestMatcher("/api/students/**")).hasRole("ADMIN") // Restricted to ADMIN role
                        .requestMatchers(new AntPathRequestMatcher("/api/students/create")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/students/delete")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/students/update/**")).permitAll()

                        .anyRequest().authenticated() // All other requests require authentication
                )
                .httpBasic() // Use basic authentication
                .and()
                .csrf().disable(); // Disable CSRF for simplicity in API development
        return http.build();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

