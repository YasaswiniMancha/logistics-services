package com.logistics.services.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.services.dto.AuthRequestDto;
import com.logistics.services.dto.AuthResponseDto;
import com.logistics.services.dto.UserRequestDto;
import com.logistics.services.dto.UserResponseDto;
import com.logistics.services.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	public final UserService userService;
	
	@PostMapping("/login")
	public AuthResponseDto login(@RequestBody AuthRequestDto request) {
		return userService.login(request);
	}
	
	@PostMapping("/create")
	public AuthResponseDto signUp(@RequestBody UserRequestDto request) {
		return userService.signup(request);
	}
	
	@GetMapping("/get/{id}")
	public UserResponseDto getUser(@PathVariable UUID id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/update/{id}")
	public UserResponseDto updateUser(@PathVariable UUID id, @RequestBody UserRequestDto request) {
		return userService.updateUser(id, request);
	}
	
	@PostMapping("/logout")
	public String logout(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader;
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}
		userService.logout(token);
		return "Logged out";
	}
	
}
