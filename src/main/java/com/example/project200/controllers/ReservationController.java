package com.example.project200.controllers;

import com.example.project200.requestDto.ReservationReqDto;
import com.example.project200.responseDto.ReservationResDto;
import com.example.project200.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping("/add-reservation")
    public String addReservation(@RequestBody ReservationReqDto reservationReqDto) {
        return reservationService.addReservation(reservationReqDto);
    }
    @PostMapping("/accept-request")
    public String acceptRequest(@RequestParam Long reservationId) {
        return reservationService.acceptRequest(reservationId);
    }
    @PostMapping("/cancel-request")
    public String cancelRequest(@RequestParam Long reservationId) {
        return reservationService.cancelRequest(reservationId);
    }
    @GetMapping("/get-all-reservations")
    public List<ReservationResDto> getAllReservations() {
        return reservationService.getAllReservations();
    }
    @GetMapping("/get-reservations-by-barber")
    public List<ReservationResDto> getReservationsByBarber(@RequestParam Long barberId) {
        return reservationService.getReservationsByBarberId(barberId);
    }




}
