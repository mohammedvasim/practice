package com.demo.jobprep.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jobprep.entity.User;
import com.demo.jobprep.exception.UserNotFoundException;
import com.demo.jobprep.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;

    public User saveUser(User user){
        return repo.save(user);
    }

    public User getUserById(Long id){
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
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

}
