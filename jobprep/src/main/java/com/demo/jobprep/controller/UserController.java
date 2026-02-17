package com.demo.jobprep.controller;

import java.util.Base64.Encoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jobprep.dto.UserRequestDto;
import com.demo.jobprep.dto.UserResponseDto;
import com.demo.jobprep.entity.User;
import com.demo.jobprep.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/All")
    public List<User> getAllUser(){
        return service.getAllUser();
    }
    
    // @GetMapping
    // public ResponseEntity<Page<UserResponseDto>> getUsers(
    //     @RequestParam(defaultValue = "0") int page,
    //     @RequestParam(defaultValue = "5") int size){
    //         return ResponseEntity.ok(service.getAllUsers(page,size));
    // }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5")int size,
        @RequestParam(defaultValue = "id")String sortBy,
        @RequestParam(defaultValue = "asc")String direction){

        Sort sort=direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        
        Pageable pageable=PageRequest.of(page,size,sort);

        return ResponseEntity.ok(service.getAllUsers1(pageable));

    }

    @GetMapping("/search/users")
    public ResponseEntity<Page<UserResponseDto>> searchUsers(
        
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5")int size){
            Pageable pageable=PageRequest.of(page,size);

            return ResponseEntity.ok(service.searchUsers(keyword, pageable));
        }


    // @PostMapping
    // public User createUser(@RequestBody User user){
    //     return service.saveUser(user);
    // }

    @PostMapping("/post")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto){
        dto.setPassword(encoder.encode(dto.getPassword()));
        return new ResponseEntity<>(service.createUser(dto),HttpStatus.CREATED);
    }

    // @GetMapping("/id/{id}")
    // public ResponseEntity<User> getUserById(@PathVariable Long id){
    //     return ResponseEntity.ok(service.getUserById(id));
    // }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/search")
    public User findByEmail(@RequestParam String email){
        return service.getUserByEmail(email);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(service.updateUser(id, user));
    }
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }


}
