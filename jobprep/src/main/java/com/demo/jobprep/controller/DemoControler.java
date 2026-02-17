package com.demo.jobprep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControler {
    
    @GetMapping("/user/home")
    public String userHome(){       
        return "welcome user";
    }
    @GetMapping("/admin/home")
    public String adminHome(){
        return "welcome admin";
    }
}
