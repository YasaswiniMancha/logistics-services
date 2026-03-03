package com.logistics.works.serviceImpl;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logistics.works.config.JwtUtil;
import com.logistics.works.dto.AuthResponseDto;
import com.logistics.works.dto.LoginRequestDto;
import com.logistics.works.dto.UserRequestDto;
import com.logistics.works.dto.UserResponseDto;
import com.logistics.works.mapper.UserMapper;
import com.logistics.works.repository.UserRepository;
import com.logistics.works.security.JwtBlacklist;
import com.logistics.works.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final JwtBlacklist jwtBlacklist;
	private final UserMapper userMapper;

	@Override
	public AuthResponseDto signup(UserRequestDto request) {
		 if(userRepo.existByUsername(request.getUsername())) {
			 throw new RuntimeException("Username already exists");
		 }
	}

	@Override
	public AuthResponseDto login(LoginRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto getUserById(UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto updateuser(UUID userId, UserRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub
		
	}

}
