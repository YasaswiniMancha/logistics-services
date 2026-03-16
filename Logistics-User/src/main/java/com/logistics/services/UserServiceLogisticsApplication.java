package com.logistics.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserServiceLogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceLogisticsApplication.class, args);
	}

}
