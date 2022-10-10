package com.example.jwt_basic.Controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Endpoint {

    @GetMapping("/v1")
    public String demo1(){
        return "Demo 1";
    }

    @GetMapping("/v2")
    @PreAuthorize("hasAnyAuthority('read')")
    public String demo2(){
        return "Demo 2";
    }

    @GetMapping("/v3")
    @PreAuthorize("""
            hasAnyAuthority('read', 'write')
            """)
    public String demo3(){
        return "Demo 3";
    }

    @GetMapping("/v4")
    @PreAuthorize("@Condition.condition()")
    public String demo4(){
        return "Demo 4";
    }
  
}
