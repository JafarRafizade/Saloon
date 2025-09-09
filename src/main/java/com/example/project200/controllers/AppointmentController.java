package com.example.project200.controllers;

import com.example.project200.entities.Status;
import com.example.project200.services.AppointmentServiceImpl;
import com.example.project200.services.serviceInterfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;


    @PostMapping("add")
    public ResponseEntity<String> addAppointment(@RequestParam Long barberId, @RequestParam Integer durationTime, @RequestParam LocalDateTime localDateTime, @RequestHeader("X-Authenticated-User-ID") String token){
        return ResponseEntity.ok(appointmentService.addAppointment(barberId,durationTime,localDateTime,token));
    }





    @PutMapping("confirm")
    public ResponseEntity<String> confirm(@RequestParam Long appointmentId, @RequestParam(required = false) Status status, @RequestParam Long barberId, @RequestHeader("X-Authenticated-User-ID") String token){
        return ResponseEntity.ok(appointmentService.confirm(appointmentId,status,barberId,token));
    }


    @PutMapping("notifyCustomer")
    public ResponseEntity<String> notifyCustomer(@RequestParam List<Long> id){
        return ResponseEntity.ok(appointmentService.notifyCustomer(id));
    }
    @GetMapping("getCustomerNumber")
    public ResponseEntity<String> callToCustomer(Long appointmentId,Long barberId,@RequestHeader("X-Authenticated-User-ID")String token){
        return ResponseEntity.ok(appointmentService.callToCustomer(appointmentId,barberId,token));
    }




}
