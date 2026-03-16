package com.logistics.services.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.logistics.services.dto.UserRequestDto;
import com.logistics.services.dto.UserResponseDto;
import com.logistics.services.entity.Roles;
import com.logistics.services.entity.User;

@Mapper(componentModel ="spring")
public interface UserMapper {
	
	User toEntity(UserRequestDto dto);
	
	@Mapping(target="roles", expression ="java(mapRoles(user.getRoles()))")
	UserResponseDto toResponseDto(User user);
	
	default Set<String> mapRoles(Set<Roles> roles){
		return roles.stream().map(Enum::name).collect(Collectors.toSet());
	}
}