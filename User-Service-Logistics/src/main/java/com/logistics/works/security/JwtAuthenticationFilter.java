package com.logistics.works.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.logistics.works.config.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter {
	
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final JwtBlacklist jwtBlacklist;
    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
    	final String authHeader = request.getHeader("Authorization");
    	String username = null;
    	String token= null;
    	if(authHeader!=null && authHeader.startsWith("Bearer ")) {
    		token = authHeader.substring(7);
    		username = jwtUtil.extractUsername(token);
    	}
    	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null && !jwtBlacklist.isBlackListed(token) && jwtUtil.validateToken(token)) {
    		
    	}
    }
}
