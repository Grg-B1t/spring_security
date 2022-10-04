package com.example.spring_security.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Tour {
    private Integer tour_id;
    private String tour_title;
    private Double tour_cost;
}
