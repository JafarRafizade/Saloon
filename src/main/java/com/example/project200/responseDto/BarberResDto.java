package com.example.project200.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BarberResDto {
    private Long id;
    private String name;
    private SaloonResDto saloon;

}
