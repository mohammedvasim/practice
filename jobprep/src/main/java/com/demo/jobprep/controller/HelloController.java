package com.demo.jobprep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jobprep.service.GreetingDTO;
import com.demo.jobprep.service.HelloService;

@RestController
@RequestMapping("/api")
public class HelloController {
    
    @Autowired
    private HelloService service;

    @GetMapping("/demo")
    public String hello(){
        return "Hello brother "+service.getMessage();
    }
 
    @PostMapping("/greet")
    public String greet(@RequestBody GreetingDTO greeting){
        return "hello "+ greeting.getName();
    }

    // @GetMapping("/user")
    // public Map<String,String> getUser(){
    //     Map<String,String> user=new HashMap<>();
    //     user.put("Vasim","developer");
    //     user.put("anisa","tester");
    //     return user;
    // }


}
