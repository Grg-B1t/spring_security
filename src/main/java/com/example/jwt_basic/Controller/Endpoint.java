package com.example.jwt_basic.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {

    @GetMapping("/v2")
    public String demoApi(){
        return "Demo";
    }
}
