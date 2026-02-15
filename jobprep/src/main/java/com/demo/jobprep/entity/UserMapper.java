package com.demo.jobprep.entity;

import com.demo.jobprep.dto.UserRequestDto;
import com.demo.jobprep.dto.UserResponseDto;

public class UserMapper {
    
    public static User toEntity(UserRequestDto dto){
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDto toDto(User user){
        UserResponseDto dto=new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }
}
