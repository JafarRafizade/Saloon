package com.example.project200.controllers;

import com.example.project200.requestDto.BarberCreateReqDto;
import com.example.project200.requestDto.BarberUpdateReqDto;
import com.example.project200.responseDto.BarberResDto;
import com.example.project200.responseDto.PaginationResDto;
import com.example.project200.services.BarberServiceImpl;
import com.example.project200.services.serviceInterfaces.BarberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barber")
@RequiredArgsConstructor
public class BarberController {
    private final BarberService barberService;
    @PostMapping("add")
    public ResponseEntity<String> addBarber(@Valid @RequestBody BarberCreateReqDto barberCreateReqDto){
        return ResponseEntity.ok(barberService.addBarber(barberCreateReqDto));
    }

    @PutMapping("update")
    public ResponseEntity<String> updateBarber(@RequestParam Long id, @Valid @RequestBody BarberUpdateReqDto barberUpdateReqDto, @RequestHeader("X-Authenticated-User-ID")String token){
        return ResponseEntity.ok(barberService.updateBarber(id,barberUpdateReqDto,token));
    }


    @DeleteMapping("delete")
    public ResponseEntity<String> deleteBarber(@RequestParam Long id, @RequestHeader("X-Authenticated-User-ID")String token){
        return ResponseEntity.ok(barberService.deleteBarber(id,token));
    }


    @GetMapping("all")
    public ResponseEntity<List<BarberResDto>> getAllBarbers(){
        return ResponseEntity.ok(barberService.getAllBarber());
    }


    @GetMapping("byId")
    public ResponseEntity<BarberResDto> getBarberById(@RequestParam Long id){
        return ResponseEntity.ok(barberService.getBarberById(id));
    }


    @GetMapping("firstName")
    public ResponseEntity<List<BarberResDto>> getBarberByFirstName(@RequestParam String firstName){
        return ResponseEntity.ok(barberService.getBarberByFirstName(firstName));
    }


    @GetMapping("lastName")
    public ResponseEntity<List<BarberResDto>> getBarberByLastName(@RequestParam String lastName){
        return ResponseEntity.ok(barberService.getBarberByLastName(lastName));
    }
    @GetMapping("byBarberShop")
    public ResponseEntity<List<BarberResDto>> getBarbersBySaloon(@RequestParam Long id){
        return ResponseEntity.ok(barberService.getBarberByBarberShop(id));
    }


    @GetMapping("onlineStatus")
    public ResponseEntity<String> getOnlineStatus(@RequestParam Long id){
        return ResponseEntity.ok(barberService.getOnlineStatus(id));
    }


    @PutMapping("updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam Long id, @RequestParam Integer newStatus, @RequestHeader("X-Authenticated-User-ID")String token){
        return ResponseEntity.ok(barberService.updateStatus(id,newStatus,token));
    }


    @GetMapping("online")
    public ResponseEntity<PaginationResDto> getBarberByOnline(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(barberService.getBarberByOnline(page,size));
    }

    @GetMapping("search")
    public ResponseEntity<PaginationResDto> searchEngine(@RequestParam String engine,@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(barberService.searchEngine(engine,page,size));
    }



}
