package com.example.project200.requestDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class SaloonReqDto {

    private String name;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}
