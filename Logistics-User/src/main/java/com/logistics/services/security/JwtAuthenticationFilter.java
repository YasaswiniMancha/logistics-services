package com.logistics.services.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.logistics.services.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
//our custom authentication filter
public class JwtAuthenticationFilter extends OncePerRequestFilter { // Ensures filter runs once per request

	private final JwtUtil jwtUtil;  // Utility class to generate & validate JWT
	private final UserDetailsService userDetailsService; // Used to load user details from database
	private final JwtBlacklist jwtBlacklist; // our Custom blacklist component to block logged-out tokens

	// This method runs for every incoming HTTP request
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");   // Extract Authorization header
		
		String username = null;  // Will store username from token
        String token = null;     // Will store JWT token
        
     // Check if header exists and starts with Bearer
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7); // Remove "Bearer " prefix (first 7 characters)
			username = jwtUtil.extractUsername(token);  // Extract username from JWT
		}
		
		 /*
        Conditions to authenticate:
        1. Username must exist
        2. No authentication already set
        3. Token should NOT be blacklisted
        4. Token must be valid (not expired, signature correct)
       */
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null
				&& !jwtBlacklist.isBlackListed(token) && jwtUtil.validateToken(token)) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // Load user from database
			
			 /*
            Create authentication object:
            - principal = userDetails
            - credentials = null (already validated)
            - authorities = roles
           */
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Attach request-specific details (IP address, session id)
			
			SecurityContextHolder.getContext().setAuthentication(authToken);  // Set authentication in security context
		}
		
		filterChain.doFilter(request, response);   // Continue filter chain 
	}
}



//SecurityContextHolder stores authenticated user for current thread


/* Step-by-step:

Every request comes in.

It checks for Authorization header.

Extracts JWT.

Validates:

Not expired

Signature correct

Not blacklisted

Loads user from DB.

Creates Authentication object.

Stores it in SecurityContext.

Allows request to proceed.
*/
