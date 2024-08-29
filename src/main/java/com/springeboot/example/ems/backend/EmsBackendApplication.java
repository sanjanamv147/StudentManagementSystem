package com.springeboot.example.ems.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
@EntityScan(basePackages = " com.springeboot.example.ems.backend")
public class EmsBackendApplication {
	public static void main(String[] args) {

		SpringApplication.run(EmsBackendApplication.class, args);
	}

}

