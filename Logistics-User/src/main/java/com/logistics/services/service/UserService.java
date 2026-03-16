package com.logistics.services.service;

import java.util.UUID;

import com.logistics.services.dto.AuthRequestDto;
import com.logistics.services.dto.AuthResponseDto;
import com.logistics.services.dto.UserRequestDto;
import com.logistics.services.dto.UserResponseDto;

public interface UserService {

	AuthResponseDto signup(UserRequestDto request);

	AuthResponseDto login(AuthRequestDto request);

	UserResponseDto getUserById(UUID userId);

	void logout(String token);

	UserResponseDto updateUser(UUID id, UserRequestDto request);
}
