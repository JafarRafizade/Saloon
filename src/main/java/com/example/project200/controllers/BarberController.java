package com.example.project200.controllers;

import com.example.project200.requestDto.BarberReqDto;
import com.example.project200.responseDto.BarberResDto;
import com.example.project200.services.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barber")
@RequiredArgsConstructor
public class BarberController {
    private final BarberService barberService;
    @PostMapping("/create-barber")
    public String createBarber(@RequestBody BarberReqDto barberReqDto) {
        return barberService.createBarber(barberReqDto);
    }

    @GetMapping("/get-all-barbers")
    public List<BarberResDto> getAllBarbers() {
        return barberService.getAllBarbers();

    }
    @GetMapping("/get-barbers-by-saloon")
    public List<BarberResDto> getBarbersBySaloon(@RequestParam Long saloonId) {
        return barberService.getBarbersBySaloon(saloonId);
    }
    @PutMapping("/update-barber")
    public String updateBarber(@RequestBody BarberReqDto barberReqDto, @RequestParam Long Id) {
        return barberService.updateBarber(barberReqDto, Id);
    }
    @DeleteMapping("/delete-barber")
    public String deleteBarber(@RequestParam Long id) {
        return barberService.deleteBarber(id);
    }


}
