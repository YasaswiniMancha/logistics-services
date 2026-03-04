package com.logistics.works.serviceImpl;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logistics.works.config.JwtUtil;
import com.logistics.works.dto.AuthResponseDto;
import com.logistics.works.dto.LoginRequestDto;
import com.logistics.works.dto.UserRequestDto;
import com.logistics.works.dto.UserResponseDto;
import com.logistics.works.entity.Roles;
import com.logistics.works.entity.User;
import com.logistics.works.exceptions.UserNotFoundException;
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
		 User user = userMapper.toEntity(request);
		 user.setPassword(passwordEncoder.encode(request.getPassword()));
		 user.getRoles().add(Roles.CUSTOMER);
		 
		 User savedUser = userRepo.save(user);
		 
		 String accessToken = jwtUtil.generateToken(savedUser);
		 
		 String refreshToken = jwtUtil.refreshToken(savedUser);
		 
		 return buildAuthResponse(savedUser, accessToken, refreshToken);
	}

	@Override
	public AuthResponseDto login(LoginRequestDto request) {
		User user = userRepo.findByUsername(request.getUsername())
				.orElseThrow(()-> new UserNotFoundException("User not found exception"));
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}
		String accessToken = jwtUtil.generateToken(user);
		String refreshToken = jwtUtil.refreshToken(user);
		
		return buildAuthResponse(user, accessToken, refreshToken);
	}

	@Override
	public UserResponseDto getUserById(UUID userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
		return userMapper.toResponseDto(user);
	}

	@Override
	public UserResponseDto updateuser(UUID userId, UserRequestDto request) {
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setState(request.getState());
		user.setAddress(request.getAddress());
		user.setCountry(request.getAddress());
		
		User updated = userRepo.save(user);
		return userMapper.toResponseDto(user);
	}

	@Override
	public void logout(String token) {
       jwtBlacklist.blacklist(token);		
	}

	private AuthResponseDto buildAuthResponse(User user, String accessToken, String refreshToken) {
		Set<String> roles= user.getRoles()
				.stream()
				.map(Enum::name)
				.collect(Collectors.toSet());
		return AuthResponseDto.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.userId(user.getId())
				.username(user.getUsername())
				.roles(roles)
				.build();
		
	}
}
