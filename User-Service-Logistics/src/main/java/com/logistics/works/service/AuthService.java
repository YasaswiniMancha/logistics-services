package com.logistics.works.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logistics.works.dto.AuthResponseDto;
import com.logistics.works.dto.LoginRequestDto;
import com.logistics.works.entity.User;
import com.logistics.works.repository.UserRepository;
import com.logistics.works.security.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	public AuthResponseDto login(LoginRequestDto request) {
		 User user = userRepository.findByUsername(request.getUsernameOrEmail()).orElseThrow(()-> new RuntimeException("Invalid username or mail"));
		 if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			 throw new RuntimeException("wrong password");
		 }
		 String token = jwtTokenProvider.generateToken(user.getId(), user.getPassword(), user.getRole().name());
		 return AuthResponseDto.builder()
				 .accessToken(token)
				 .tokenType("Bearer")
				 .userId(user.getId())
				 .username(user.getUsername())
				 .role(user.getRole().name())
				 .build();
	 }

}
