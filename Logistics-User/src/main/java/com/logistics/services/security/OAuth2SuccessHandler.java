package com.logistics.services.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.logistics.services.entity.User;
import com.logistics.services.util.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {

	private final JwtUtil jwtUtil;
	
	@Value("${app.oauth2.redirect-uri}")
	private String redirectUri;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
		throws java.io.IOException{
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User user = userDetails.getUser();
		String accessToken = jwtUtil.generateToken(user);
		String refreshToken = jwtUtil.generateRefreshToken(user);
		log.info("OAuth2 login success for user ={}",user.getEmail());
		 String targetUrl = UriComponentsBuilder
	                .fromUriString(redirectUri)
	                .queryParam("token",   accessToken)
	                .queryParam("refresh", refreshToken)
	                .build().toUriString();
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
			
	}
	
}
