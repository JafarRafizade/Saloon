package com.example.project200.controllers;

import com.example.project200.requestDto.SaloonReqDto;
import com.example.project200.responseDto.SaloonResDto;
import com.example.project200.services.SaloonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saloon")
public class SaloonController {
    private final SaloonService saloonService;
    @PostMapping("/create-saloon")
    public String createSaloon(@RequestBody SaloonReqDto saloonReqDto) {
        return saloonService.createSaloon(saloonReqDto);
    }
    @GetMapping("/get-all-saloons")
    public List<SaloonResDto> getAllSaloons() {
        return saloonService.getAllSaloons();
    }
    @GetMapping("/get-saloons-by-location")
    public List<SaloonResDto> getSaloonsByLocation(@RequestParam String location) {
        return saloonService.getSaloonsByLocation(location);
    }
    @PutMapping("/update-saloon")
    public String updateSaloon(@RequestBody SaloonReqDto saloonReqDto, @RequestParam Long id) {
        return saloonService.updateSaloon(id, saloonReqDto);
    }
    @DeleteMapping("/delete-saloon")
    public String deleteSaloon(@RequestParam Long saloonId) {
        return saloonService.deleteSaloon(saloonId);
    }


}
