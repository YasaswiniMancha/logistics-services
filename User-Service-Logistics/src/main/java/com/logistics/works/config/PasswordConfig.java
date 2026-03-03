package com.logistics.works.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class PasswordConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//	        http
//	            .csrf(csrf-> csrf.disable())
//	            .authorizeHttpRequests( auth -> auth
//	                     .requestMatchers("/api/logistics/track/**").permitAll()
//	                     .anyRequest().authenticated() 
//	            )
//	            .oauth2ResourceServer(oauth-> oauth.jwt(jwt->{}));
//	            
//
//	        return http.build();
//	    }
}
