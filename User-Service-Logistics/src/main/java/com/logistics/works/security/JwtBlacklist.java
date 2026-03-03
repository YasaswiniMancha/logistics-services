package com.logistics.works.security;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class JwtBlacklist {

	private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();
	
	public void blacklist(String token) {
		blacklistedTokens.add(token);
	}
	
	public boolean isBlackListed(String token) {
		return blacklistedTokens.contains(token);
	}
}
