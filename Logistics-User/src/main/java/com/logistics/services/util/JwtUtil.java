package com.logistics.services.util;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.logistics.services.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {

	/**
     * jwt.secret — base64 or plain secret from application.yml
     * Must be at least 256 bits (32 chars) for HS256.
     * NEVER hardcode this — always inject from config/env.
     */
	@Value("${jwt.secret}")
	private String secret;

	
	
	 /**
     * jwt.expiration — access token TTL in milliseconds.
     * Recommended: 900000 (15 minutes).
     * Short TTL limits damage if token is stolen.
     */
	@Value("${jwt.expiration}")
	private long accessTokenExpiration;

	
	
	/**
     * Signing key derived from the secret.
     * Keys.hmacShaKeyFor() converts raw bytes → cryptographic Key object.
     * Stored once after init — not recreated on every call (performance).
     */
	private Key key;

	
	
	 /**
     * Refresh token lives 15 days.
     * Used ONLY to issue a new access token — never for API access directly.
     * Stored in DB (RefreshToken entity) so it can be revoked.
     */
	private final long REFRESH_TOKEN_EXPIRATION  = 15 * 24 * 60 * 60 * 1000; // 15 days

	
	
	
	 /**
     * @PostConstruct — runs once after Spring injects @Value fields.
     * We build the signing Key here because @Value is not yet available
     * in the constructor at injection time.
     *
     * Keys.hmacShaKeyFor(byte[]):
     *   Built-in JJWT method that creates an HMAC-SHA key from raw bytes.
     *   Validates that the key is long enough for the algorithm.
     */
	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	//Access Token
	  /* generateToken(User user)
	     *
	     * WHY ROLES IN TOKEN:
	     *   Embedding roles as claims means downstream services and the
	     *   API Gateway can enforce RBAC without calling User Service.
	     *   This is the stateless RBAC pattern.
	     *
	     * WHY userId IN TOKEN:
	     *   Every service needs the userId to scope queries
	     *   (e.g. "get orders for THIS user"). Embedding it avoids a
	     *   round-trip to User Service on every request.
	     *
	     * Jwts.builder()          — starts building a JWT
	     * .setSubject()           — standard "sub" claim = username/email
	     * .claim("roles", ...)    — custom claim: Set of role names
	     * .claim("userId", ...)   — custom claim: UUID string
	     * .setIssuedAt()          — standard "iat" claim = issue timestamp
	     * .setExpiration()        — standard "exp" claim = expiry timestamp
	     * .signWith(key, algo)    — signs with HMAC-SHA256
	     * .compact()              — serializes to URL-safe string
	     *
	     * BUG FIX from original:
	     *   Original used user.getRole() mapped with Enum::name → "role" claim.
	     *   Fixed to use "roles" (plural) to match extractRoles() which also
	     *   reads "roles". Mismatch caused NPE at extraction.
	     */
	public String generateToken(User user) {
		Set<String> role = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());

		return Jwts.builder()
				.setSubject(user.getEmail())  // principal identifier
				.claim("userId", user.getUuid())  //business ID
				.claim("roles", role)  //for RBAC - Note: key is "roles"
				.setIssuedAt(new Date())
				.setExpiration(new Date(
						System.currentTimeMillis() + accessTokenExpiration))
				.signWith(key, SignatureAlgorithm.HS256)  // HMAC-SHA256 signature
				.compact();  // serializes to compact JWT string

	}

	
	//Generate Refresh Token
	 // Refresh token carries ONLY subject (email/uuid)
    // Intentionally minimal — less damage if leaked
    // Subject = uuid (not email) so email changes don't invalidate refresh tokens
	public String generateRefreshToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUuid())    //uuid as subject for refresh
				.setIssuedAt(new Date())     
				.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION )) 
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	
	 // ── CHECK EXPIRY ──────────────────────────────────────────────
    // Used in refresh token flow — returns true if access token expired
    // but signature is still valid (allows safe refresh)
    public boolean isTokenExpired(String token) {
        try {
            return extractAllClaims(token)
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            return true;  // expired = yes, expired
        }
    }
    
	
	
	// ── PRIVATE: EXTRACT ALL CLAIMS ───────────────────────────────
    // Core parsing method used by all extract* methods
    // parseClaimsJws() verifies:
    //   - HMAC signature using our key
    //   - Token structure (3 Base64 segments)
    //   - Expiration date
    // Throws JwtException subtypes on any failure
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJwt(token)
				.getBody();
	}
	
	// ── EXTRACT USERNAME (email) ──────────────────────────────────
    // Called by JwtAuthenticationFilter to identify user from token
    // Returns email which is used to load UserDetails from DB
	public String extractUsername(String token) {
		 return extractAllClaims(token).getSubject();
	}
	
	// ── EXTRACT ROLES ─────────────────────────────────────────────
    // Called by JwtAuthenticationFilter to build GrantedAuthority list
    // Key must match what generateToken() stored: "roles" (not "role")
	@SuppressWarnings("unchecked")
	public Set<String> extractRoles(String token){
		return (Set<String>) extractAllClaims(token).get("roles");
	}
	
	// ── VALIDATE TOKEN ────────────────────────────────────────────
    // Checks:
    //   1. Signature is valid (key matches)
    //   2. Token is not expired
    //   3. Token is well-formed (not null, not malformed)
    // Called in JwtAuthenticationFilter BEFORE loading user from DB
	 public boolean validateToken(String token) {
	        try {
	            // parseClaimsJws() — IMPORTANT: use Jws (with 's') not Jwt
	            // Jws = signed token → verifies signature
	            // Jwt = unsigned → does NOT verify signature (security hole!)
	            extractAllClaims(token);
	            return true;
	        } catch (ExpiredJwtException e) {
	            log.warn("JWT expired: {}", e.getMessage());
	        } catch (UnsupportedJwtException e) {
	            log.warn("Unsupported JWT: {}", e.getMessage());
	        } catch (MalformedJwtException e) {
	            log.warn("Malformed JWT: {}", e.getMessage());
	        } catch (io.jsonwebtoken.security.SecurityException e) {
	            log.warn("Invalid JWT signature: {}", e.getMessage());
	        } catch (IllegalArgumentException e) {
	            log.warn("JWT claims empty: {}", e.getMessage());
	        }
	        return false;
	    }
	
	// ── EXTRACT USER ID ───────────────────────────────────────────
    // Called by services that need the business userId from token
    // (e.g. OrderService reads userId to associate order with user)
    public String extractUserId(String token) {
        return extractAllClaims(token).get("userId", String.class);
    }
    
	// Called by JwtBlacklist to get expiration date for TTL calculation
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

}
