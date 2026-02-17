package com.demo.jobprep.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


//used for post requests
public class UserRequestDto {
    
    @NotBlank(message= " Name is required")
    private String name;

    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private String email;

    private String password;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    


}
