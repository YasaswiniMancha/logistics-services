package com.logistics.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logistics.services.entity.User;
import com.logistics.services.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/*WHY this class?
 Spring Security's AuthenticationProvider needs a way to load
user data during the login process.
  It calls loadUserByUsername(email) → gets UserDetails → compares password.
    Without this: Spring has no idea how to find your users.
     With this: Authentication works automatically.
      Also used by JwtAuthenticationFilter to reload user on every
       authenticated request (to get latest authorities from DB).
       */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("user not found with email: " + email));
		return new UserDetailsImpl(user);
	}

}
