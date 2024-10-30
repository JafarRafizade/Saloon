package com.example.project200.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter

public class SaloonResDto {
    private Long id;

    private String name;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
