package com.example.project200.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ReservationReqDto {
    private Long barberId;
    private List<Long> serveIds;
    private LocalDateTime startTime;
}
