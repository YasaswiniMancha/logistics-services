package com.logistics.works.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.logistics.works.dto.UserRequestDto;
import com.logistics.works.dto.UserResponseDto;
import com.logistics.works.entity.Role;
import com.logistics.works.entity.User;

@Mapper(componentModel ="spring")
public interface UserMapper {
	
	User toEntity(UserRequestDto dto);
	
	@Mapping(target="roles", expression ="java(mapRoles(user.getRoles()))")
	UserResponseDto toResponseDto(User user);
	
	default Set<String> mapRoles(Set<Role> roles){
		return roles.stream().map(Enum::name).collect(Collectors.toSet());
	}
}