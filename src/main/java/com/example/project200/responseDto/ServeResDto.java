package com.example.project200.responseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServeResDto {
    private Long id;
    private String name;
    private Double price;
    private int requiredTime;
    private Long saloonId;
    private Long barberId;
//    private Barber barber;
//    private Saloon saloon;
}
