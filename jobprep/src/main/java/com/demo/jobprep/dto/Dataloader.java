package com.demo.jobprep.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.jobprep.entity.User;
import com.demo.jobprep.repository.UserRepository;

@Component
public class Dataloader implements CommandLineRunner{
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception{
        if (repo.findByUsername("vasim").isEmpty()) {

            User user = new User();
            user.setUsername("vasim");
            user.setPassword(encoder.encode("1234"));
            user.setRole("ROLE_ADMIN");
            repo.save(user);
        }
    }
}
