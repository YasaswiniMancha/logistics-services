package com.logistics.services.serviceImpl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.UserCreatedEvent;
import com.logistics.services.dto.AuthRequestDto;
import com.logistics.services.dto.AuthResponseDto;
import com.logistics.services.dto.UserRequestDto;
import com.logistics.services.dto.UserResponseDto;
import com.logistics.services.entity.Roles;
import com.logistics.services.entity.User;
import com.logistics.services.exceptions.UserNotFoundException;
import com.logistics.services.kafka.UserEventProducer;
import com.logistics.services.mapper.UserMapper;
import com.logistics.services.repository.UserRepository;
import com.logistics.services.security.JwtBlacklist;
import com.logistics.services.service.UserService;
import com.logistics.services.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final JwtBlacklist jwtBlacklist;
	private final UserMapper userMapper;
	private final UserEventProducer userEventProducer;

	@Override
	public AuthResponseDto signup(UserRequestDto request) {
		 if(userRepo.existByUsername(request.getUsername())) {
			 throw new RuntimeException("Username already exists");
		 }
		 User user = userMapper.toEntity(request);
		 user.setPassword(passwordEncoder.encode(request.getPassword()));
		 user.getRoles().add(Roles.CUSTOMER);
		 
		 User savedUser = userRepo.save(user);
		 
		 //kafka event start
		 UserCreatedEvent event = UserCreatedEvent.builder()
				 .id(savedUser.getId())
				 .username(savedUser.getUsername())
				 .email(savedUser.getEmail())
				 .phoneNumber(savedUser.getPhoneNumber())
				 .build();

			event.setEventId(UUID.randomUUID());
			event.setEventType("UserCreatedEvent");
			event.setEventVersion("1.0");
			event.setSourceService("user-service");
			event.setTimestamp(LocalDateTime.now());

				 
		 userEventProducer.publishUserCreatedEvent(event);
		 //ended kafka event
		 
		 
		 String accessToken = jwtUtil.generateToken(savedUser);
		 
		 String refreshToken = jwtUtil.refreshToken(savedUser);
		 
		 return buildAuthResponse(savedUser, accessToken, refreshToken);
		 
		 /*
Client → Signup API
        ↓
User saved in DB
        ↓
Kafka Event Published
        ↓
Topic: user-created-topic
        ↓
Other microservices (Logistics Service) consume it

		  */
	}

	@Override
	public AuthResponseDto login(AuthRequestDto request) {
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
	public UserResponseDto updateUser(UUID userId, UserRequestDto request) {
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setState(request.getState());
		user.setAddress(request.getAddress());
		user.setCountry(request.getCountry());
		User updatedUser= userRepo.save(user);
		return userMapper.toResponseDto(updatedUser);
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
