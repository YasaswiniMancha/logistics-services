package com.logistics.works.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
 
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.access-token-expiration}")
	private long jwtExpiration;
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}
	
	public String generateToken(Long userId, String username, String role) {
		Date now= new Date(); //now: Captures the exact moment the token is created.
		Date expiryDate= new Date(now.getTime()+jwtExpiration); //Calculates when the token should die.
		//ex: we take the current time and add a duration (e.g., 30 minutes). Once this time passes, the token is rejected by the server, which is a crucial security layer.
		return Jwts.builder()
				.setSubject(username) //This is usually the unique identity of the user
				.claim("userId", userId) //Adds a custom piece of data. Here, you're embedding the database ID.
				.claim("role", role) //Another custom claim. This allows your frontend or other services to know the user's permissions (e.g., ADMIN) without another database lookup.
				.setIssuedAt(now) //Records the creation time
				.setExpiration(expiryDate) //Sets the exp claim.
				.signWith(getSigningKey(),SignatureAlgorithm.HS256) //takes all the data above and runs it through a hashing algorithm (HS256) using Secret Key. If a hacker tries to change the "role" from USER to ADMIN, the signature will no longer match the content, and the token will be invalid.
				.compact();
		         //This finalizes the process. It converts the entire object into three-part string separated by dots (e.g., header.payload.signature).
		
	}
	
}
