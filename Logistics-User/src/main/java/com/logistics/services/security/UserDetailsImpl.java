package com.logistics.services.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.logistics.services.entity.User;
import com.logistics.services.entity.UserStatus;

import lombok.Getter;

/*
WHY this class?
Spring Security doesn't know about your User entity.
It works with UserDetails interface internally.
This class is the BRIDGE between your User entity and Spring Security.

UserDetails interface contract requires:
getAuthorities()  → what roles/permissions does this user have?
getPassword()     → BCrypt encoded password for AuthenticationProvider to verify
getUsername()     → unique identifier (email here)
isAccountNonExpired(), isAccountNonLocked(), etc. → account state flags

HOW IT WORKS:
UserDetailsServiceImpl loads User from DB
Wraps it in UserDetailsImpl
Spring Security compares getPassword() with submitted password via BCrypt
getAuthorities() is used by @PreAuthorize for every secured method call */
public class UserDetailsImpl implements UserDetails, OAuth2User {

	// Store the full User entity — gives access to uuid, roles, status etc.
	@Getter
	private final User user;

	// OAuth2 attributes — populated when logging in via Google/GitHub, null for JWT
	// auth
	private Map<String, Object> attributes;

	// Constructor for JWT / local authentication
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	// Constructor for OAuth2 authentication (Google, GitHub)
	// attributes = raw OAuth2 provider response (name, email, picture etc.)
	public UserDetailsImpl(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	// ── CORE RBAC METHOD ──────────────────────────────────────────
	// getAuthorities() maps your UserRole set to Spring's GrantedAuthority
	//
	// WHY SimpleGrantedAuthority?
	// It's Spring's built-in implementation of GrantedAuthority.
	// @PreAuthorize("hasAuthority('ADMIN')") checks against these strings.
	// Convention: NO "ROLE_" prefix when using hasAuthority()
	// Use "ROLE_" prefix + hasRole() for alternative approach
	// We use hasAuthority() throughout — cleaner with enums
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
	}

	// Returns BCrypt encoded password
	// AuthenticationProvider calls this + BCryptPasswordEncoder.matches()
	// to verify submitted password without decoding (one-way hash)
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// Returns email as the unique username identifier
	// This is what JwtUtil.generateToken() stores as JWT subject
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	// Account never expires in our system — always true
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Returns false if account is suspended (e.g. fraud investigation)
	// Causes LockedException to be thrown during authentication
	@Override
	public boolean isAccountNonLocked() {
		return user.getStatus() != UserStatus.SUSPENDED && user.getStatus() != UserStatus.DEACTIVATED;
	}

	// Credentials never expire — we handle this via token expiry instead
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Account is enabled only if ACTIVE or UNVERIFIED (can still call verify-otp)
	// DEACTIVATED users get DisabledException during authentication
	@Override
	public boolean isEnabled() {
		return user.getStatus() == UserStatus.ACTIVE || user.getStatus() == UserStatus.UNVERIFIED;
	}

	// ── OAuth2User interface methods ──────────────────────────────

	// Returns raw attributes from OAuth2 provider (Google: "sub", "email", "name",
	// "picture")
	// OAuth2SuccessHandler reads these to create/update user in DB
	@Override
	public Map<String, Object> getAttributes() {
		return attributes != null ? attributes : new HashMap<>();
	}

	// Convenience method for other services to get UUID without going through user
	public String getUuid() {
		return user.getUuid();
	}

	// OAuth2User name — uses email as primary identifier
	@Override
	public String getName() {
		return user.getEmail();
	}
}
