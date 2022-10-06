package com.example.jwt_basic.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c")
public class Endpoint2 {
    
    @GetMapping("/c1")
    public String demoApi(){
        return "test";
    }

    @PostMapping("/c2")
    public String demoApi2() {
        return "Post test";
    }
}
