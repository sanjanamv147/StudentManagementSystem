package com.springeboot.example.ems.backend;//package com.springeboot.example.ems.backend;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                       // .requestMatchers("/api/users/create").authenticated()  // Allow unauthenticated access to user creation
//                      //  .requestMatchers("/api/users/**").authenticated()  // Require authentication for all other user-related endpoints
//                       // .anyRequest().permitAll()  // Require authentication for all other requests
//                        .requestMatchers("/api/employees").hasRole("ADMIN")  // Only admins can perform CRUD operations
//                        .requestMatchers("/api/users").hasRole("USER")    // Users can only read and modify
//                        .anyRequest().authenticated()  // Require authentication for all other requests
//                )
//                .httpBasic(Customizer.withDefaults()) // Enable Basic Authentication
//                .csrf(csrf -> csrf.disable());  // Disable CSRF (not recommended for production)
//
//        return http.build();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//
//
//}
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user = User.builder()
////                .username("user")
////                .password("{noop}password")  // {noop} means no password encoding
////                .roles("USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//
//}
//package com.example.spring.project.ems.Service;
//
//import com.example.spring.project.ems.Entity.UserEntity;
//import com.example.spring.project.ems.Repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Service;
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole().replace("ROLE_", ""))
//                .build();
//    }
//}



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
public class SecurityConfig implements  d {
    @Autowired
    @Lazy
    private CustomUserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/users")).permitAll() // No authentication required for user creation
                        .requestMatchers(new AntPathRequestMatcher("/api/employees/**")).hasRole("ADMIN") // Restricted to ADMIN role
                        .requestMatchers(new AntPathRequestMatcher("/api/employees/create")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/employees/delete")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/employees/update/**")).permitAll()

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

