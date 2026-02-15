package com.demo.jobprep.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.jobprep.dto.UserRequestDto;
import com.demo.jobprep.dto.UserResponseDto;
import com.demo.jobprep.entity.User;
import com.demo.jobprep.entity.UserMapper;
import com.demo.jobprep.exception.UserNotFoundException;
import com.demo.jobprep.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository repo;

    // public User saveUser(User user){
    //     return repo.save(user);
    // }

    public UserResponseDto createUser(UserRequestDto dto){
        User user=UserMapper.toEntity(dto);
        return UserMapper.toDto(repo.save(user));
    }


    // public User getUserById(Long id){
    //     return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    // }

    public UserResponseDto getUserById(Long id){
        User user=repo.findById(id).orElseThrow(()-> new UserNotFoundException("Useer not found with id "+ id));
        return UserMapper.toDto(user);
    }

    public User getUserByEmail(String email){
        return repo.findByEmail(email).orElse(null);
    }

    public List<User> getAllUser(){
        return repo.findAll();
    }

    public User updateUser(Long id,User newUser){
        User olduser=repo.findById(id).orElse(null);

        olduser.setName(newUser.getName());
        olduser.setEmail(newUser.getEmail());
        return repo.save(olduser);
    }

    public boolean deleteUser(Long id){
        User user=repo.findById(id).orElse(null);
        if(user != null){
            repo.delete(user);
            return true;
        }
        return false;
    }


    // public Page<UserResponseDto> getAllUsers(int page,int size){

    //     Pageable pageable=PageRequest.of(page,size,
    //         Sort.by("name").ascending()
    //     );

    //     Page<User> usersPage=repo.findAll(pageable);
    //     return usersPage.map(UserMapper::toDto);

    // }
    
    //Dynaminc sorting

    public Page<UserResponseDto> getAllUsers1(Pageable pageable){
        Page<User> userPage=repo.findAll(pageable);
        return userPage.map(UserMapper::toDto);
    }
    
    public Page<UserResponseDto> searchUsers(String keyword,Pageable pageable){
        Page<User> userPage=repo.findByEmailContaining(keyword, pageable);
            return userPage.map(UserMapper::toDto);
    }


}
