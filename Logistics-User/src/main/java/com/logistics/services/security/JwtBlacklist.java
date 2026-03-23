package com.logistics.services.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.logistics.services.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtBlacklist {

	// WHY Redis instead of ConcurrentHashMap?
	//
	// ConcurrentHashMap problems:
//	     1. Lost on application restart — logged-out tokens work again after redeploy
//	     2. Memory leak — tokens accumulate forever, never cleaned up
//	     3. Not shared across multiple instances (horizontal scaling breaks)
//  Redis advantages:
//  1. Persistent across restarts
//  2. TTL: token auto-expires from blacklist when JWT expires anyway
//     (no cleanup needed — Redis handles it)
//  3. Shared across all instances — works with load balancer
//  4. O(1) lookup — same performance as HashMap
	private final RedisTemplate<String, String> redisTemplate;

	private final JwtUtil jwtUtil;

	private static final String BLACKLIST_PREFIX = "jwt:blacklist:";

	// ── BLACKLIST A TOKEN ─────────────────────────────────────────
	// Called on logout or when admin deactivates a user
	// TTL is set to token's remaining lifetime — auto-cleanup
	//
	// WHY store value "true"?
	// Redis SET requires a value. We only care about key existence.
	// The token string itself IS the key (with prefix).
	public void blacklist(String token) {
		try {
			Date expiration = jwtUtil.extractExpiration(token);
			long remainingMs = expiration.getTime() - System.currentTimeMillis();
			if (remainingMs > 0) {
				redisTemplate
					.opsForValue()
					.set(BLACKLIST_PREFIX + token,
						"blacklisted", 
						 remainingMs,
						 TimeUnit.MILLISECONDS);
				log.info("Token blacklisted, TTL={}ms", remainingMs);
			}
		} catch (Exception e) {
			log.error("Failed to blacklist token: ", e.getMessage());
		}
	}

	public boolean isBlackListed(String token) {
		try {
			Boolean exists = redisTemplate.hasKey(token);
			return Boolean.TRUE.equals(exists);
		} catch (Exception e) {
			// If Redis is down — fail open (allow request) or fail closed?
            // For security: fail closed (treat as blacklisted = block request)
            // For availability: fail open (allow request, log warning)
            // We choose fail open + alert (availability over security for brief Redis outage)
			log.error("Redis blacklist check failed — treating as not blacklisted: {}", e.getMessage());
			return false;
		}
	}
	
}
