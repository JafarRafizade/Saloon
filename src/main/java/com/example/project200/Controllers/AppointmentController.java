package com.example.project200.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barbers")
@RequiredArgsConstructor
public class AppointmentController {
    @GetMapping("/getAppointments")
    public String getBarberAppointments() {
        return null;
    }
    @PostMapping("/request-appointment")
    public String requestAppointment() {
        return null;
    }
    @PutMapping("/update-appointment")
    public String updateAppointment() {
        return null;
    }
    @DeleteMapping("/delete-appointment")
    public String deleteAppointment() {
        return null;
    }




}
