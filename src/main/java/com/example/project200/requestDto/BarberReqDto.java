package com.example.project200.requestDto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BarberReqDto {
    private String name;
    private Long phoneNumber;
    private String email;
    private Long saloonId;



}
