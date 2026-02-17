package com.demo.jobprep.entity;

import com.demo.jobprep.dto.UserRequestDto;
import com.demo.jobprep.dto.UserResponseDto;

public class UserMapper {
    
    public static User toEntity(UserRequestDto dto){
        User user=new User();
        user.setUsername(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public static UserResponseDto toDto(User user){
        UserResponseDto dto=new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }
}
