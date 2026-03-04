package com.logistics.works.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logistics.works.dto.AuthResponseDto;
import com.logistics.works.dto.LoginRequestDto;
import com.logistics.works.dto.UserRequestDto;
import com.logistics.works.dto.UserResponseDto;

public interface UserService {

	AuthResponseDto signup(UserRequestDto request);

	AuthResponseDto login(LoginRequestDto request);

	UserResponseDto getUserById(UUID userId);

	UserResponseDto updateuser(UUID userId, UserRequestDto request);

	void logout(String token);
}
