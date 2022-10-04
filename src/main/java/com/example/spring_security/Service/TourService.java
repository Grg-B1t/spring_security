package com.example.spring_security.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_security.Model.Tour;


@Service
public class TourService implements  TourServiceInterface{

    List<Tour> tours = new ArrayList<>();
   
    {
        tours.add(new Tour(1, "France tour", 200.00));
        tours.add(new Tour(2, "England tour", 201.00));
        tours.add(new Tour(3, "Turkey tour", 202.00));
        tours.add(new Tour(4, "Hawai tour", 203.00));
        tours.add(new Tour(5, "Pataya tour", 204.00));

    }

    @Override
    public List<Tour> getAllTours() {
        return tours;
    }

    
}
