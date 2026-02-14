package com.demo.jobprep.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    
    public String getMessage(){
        return "hello my devil ";
    }

    public String getUser(String name){
        return "Hello "+name;
    }

}
