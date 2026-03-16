package com.logistics.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.logistics.services.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	//our custom JWT filter
	private final JwtAuthenticationFilter jwtFilter;
	
	/* SecurityFilterChain replaces old WebSecurityConfigurerAdapter
     in Spring Boot 3.
     
     This method defines:
     - Which URLs are secured
     - Session policy
     - Filter order
      */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception{
		
		/*
        Disable CSRF
        Why?
        - CSRF protection is mainly for session-based authentication.
        - We are using JWT (stateless).
        - So CSRF token is not required.
        
       */
		https.csrf(csrf -> csrf.disable())
		
		
		/*
		 make application STATELESS ,Without this: Spring creates session and JWT becomes meaningless
		 means:
		 - no HTTP session will be created
		 - Every request must carry JWT token
		 - server does not remember users
		  */
		.sessionManagement(
				session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		
		    /* Define Authorization Rules 
		       -/api/auth/**   -> allowed without authentication  
		        (signup, login endpoints)
		       - any other request must be authenticated
		    */
		.authorizeHttpRequests(auth-> auth.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
		);
		
		/*   Add our JwtAuthenticationFilter BEFORE
		 Why?
         - Spring Security already has many built-in filters.
         - UsernamePasswordAuthenticationFilter handles form login.
         - We want JWT validation to happen BEFORE that.
         If we don't add this:
         - JWT will never be validated.
         - SecurityContext will remain empty.
         - All protected APIs return 403.
          */
		https.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	
		/*
        Finally build the SecurityFilterChain object.
        This activates all above configurations.
       */
		return https.build();
		
	}
	
	
	/*
    AuthenticationManager is required if:
    - You want to manually authenticate user
    - Used in login process
    
    Spring provides it through AuthenticationConfiguration.
   */
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}

}
