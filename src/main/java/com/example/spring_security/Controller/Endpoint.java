package com.example.spring_security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.Model.Tour;
import com.example.spring_security.Service.TourService;

@RestController
@RequestMapping("/api")
public class Endpoint {
    TourService ts;


    @Autowired
    public Endpoint(TourService ts) {
        this.ts = ts;
    }

    @GetMapping("/getAllTours")
    ResponseEntity<List<Tour>> getAllTours(){
        try {
            return new ResponseEntity<>(ts.getAllTours(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
