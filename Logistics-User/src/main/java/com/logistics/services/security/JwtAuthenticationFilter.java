package com.logistics.services.security;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.logistics.services.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//
//WHAT IT DOES:
//Intercepts EVERY HTTP request.
//Extracts JWT from Authorization header.
//Validates it (signature + expiry + not blacklisted).
//Loads user from DB.
//Sets authentication in SecurityContextHolder.
//Request proceeds to controller with authenticated principal.
//
//WHY OncePerRequestFilter?
//Guarantees filter runs EXACTLY once per request.
//Without this: filter could execute multiple times in a forward/include chain.
//
//STATELESS SESSION MANAGEMENT:
//No session is created or used — enforced by SessionCreationPolicy.STATELESS
//in SecurityConfig. Every request carries its own JWT — server is memoryless.
//
//PROTECTION AGAINST COMMON ATTACKS:
//- Signature forgery: validateToken() checks HMAC signature
//- Token replay after logout: isBlackListed() check
//- Expired tokens: validateToken() checks expiry
//- Brute force: failed attempts tracked in User entity

@Slf4j
@Component
@RequiredArgsConstructor
//our custom authentication filter
public class JwtAuthenticationFilter extends OncePerRequestFilter { // Ensures filter runs once per request

	private final JwtUtil jwtUtil;  // Utility class to generate, validate and claim JWT Tokens
	
	private final UserDetailsService userDetailsService; // Used to load user details from database by email
	
	private final JwtBlacklist jwtBlacklist; // JwtBlacklist: Redis-backed blacklist for logged-out tokens

	// This method runs for every incoming HTTP request
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain)
			throws ServletException, IOException {
		
		// Step 1: Extract Authorization header
        // Format: "Bearer eyJhbGciOiJIUzI1NiJ9..."
		final String authHeader = request.getHeader("Authorization");   // Extract Authorization header
		
		String username = null;  // Will store username from token
        String token = null;     // Will store JWT token
        
        // Check if header exists and starts with Bearer
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7); // strip "Bearer " prefix
			username = jwtUtil.extractUsername(token); // get email from JWT subject
		}
		
		/*Validate and set authentication
        Conditions to authenticate:
        1. Username must exist
        2. No authentication already set
        3. Token should NOT be blacklisted
        4. Token must be valid (not expired, signature correct)
       */
		if (username != null 
				&& SecurityContextHolder.getContext().getAuthentication() == null
				&& !jwtBlacklist.isBlackListed(token) 
				&& jwtUtil.validateToken(token)) {
			
			// Step 4: Load full user from DB
            // This ensures deactivated users are blocked even with valid token
            // (until token expires — for immediate blocking use Kafka + blacklist)
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // Load user from database
			
            // Step 5: Build authorities from JWT roles claim
            // Reading from JWT (not DB) to avoid extra DB query on every request
            // Roles in JWT are refreshed on login — for immediate role updates
            // the API Gateway listens to user.role.changed Kafka event and
            // forces re-auth by invalidating the old token
			Set<String> roles = jwtUtil.extractRoles(token);
			var authorities = roles.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			
			  // Step 6: Create Spring Security Authentication object
            // UsernamePasswordAuthenticationToken(principal, credentials, authorities)
            //   principal   = UserDetails (contains user info)
            //   credentials = null (already verified via JWT — no password needed)
            //   authorities = GrantedAuthority list built from JWT roles
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, authorities);
			
			 // Step 7: Attach request metadata (IP address, session ID)
            // Used for audit logging and suspicious activity detection
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
			
			
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
			   // Step 8: Store in SecurityContextHolder
            // SecurityContextHolder is ThreadLocal — holds auth for current request thread
            // All subsequent code in this request can call:
            //   SecurityContextHolder.getContext().getAuthentication()
            // @AuthenticationPrincipal in controllers reads from here
            // @PreAuthorize reads authorities from here
			SecurityContextHolder.getContext().setAuthentication(authToken); 
			
			log.debug("Auth set for user={} roles={}", username, roles);
		}
		
		
		 // Step 9: Always continue filter chain — even if auth failed
        // SecurityConfig's authorizeHttpRequests() will reject unauthenticated
        // requests to protected routes with 403 Forbidden
		filterChain.doFilter(request, response);   // Continue filter chain 
	}
}


