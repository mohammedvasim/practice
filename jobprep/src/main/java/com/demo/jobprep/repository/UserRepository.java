package com.demo.jobprep.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.jobprep.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User>findByEmail(String email);
    Page<User> findByEmailContaining(String keyword,Pageable pageable);
}
