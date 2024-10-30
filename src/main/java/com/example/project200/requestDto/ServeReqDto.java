package com.example.project200.requestDto;

import lombok.*;

@Data
public class ServeReqDto {

    private String name;
    private Double price;
    private int requiredTime;
    private Long barberId;
    private Long saloonId;

}