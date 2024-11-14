package com.example.project200.responseDto;

import com.example.project200.entities.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ReservationResDto {
    private Long id;
    private Long barberId;
    private List<Long> serviceIds;
    private LocalDateTime startTime;
    private Status status;

}
