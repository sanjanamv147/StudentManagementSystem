package com.example.spring.project.ems;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface Security {
    void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception;
}
