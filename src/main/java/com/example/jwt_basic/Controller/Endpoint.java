package com.example.jwt_basic.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v")
public class Endpoint {

    @GetMapping("/v1")
    public String demoApi(){
        return "Demo";
    }

    @PostMapping("/v2")
    public String demoApi2() {
        return "Post Demo";
    }
}
