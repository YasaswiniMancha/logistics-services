package com.logistics.works.config;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.logistics.works.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long accessTokenExpiration;

	private Key key;

	private final long refreshTokenExpiration = 15 * 24 * 60 * 60 * 1000; // 15 days

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(User user) {
		Set<String> role = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());

		return Jwts.builder().setSubject(user.getUsername()).claim("role", role)
				.claim("userId", user.getId().toString()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
				.signWith(key, SignatureAlgorithm.HS256).compact();

	}

	public String refreshToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJwt(token)
				.getBody();
	}
	
	public String extractUsername(String token) {
		 return extractAllClaims(token).getSubject();
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> extractRoles(String token){
		return (Set<String>) extractAllClaims(token).get("roles");
	}
	
	public boolean validateToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		} catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
}
